import amqp from 'amqplib';
import { deleteTodosByUser } from './../services/todoService';

export async function startUserDeletedConsumer() {
  const connection = await amqp.connect('amqp://localhost');
  const channel = await connection.createChannel();
  await channel.assertQueue('user.deleted', { durable: false });

  channel.consume('user.deleted', async (msg) => {
    if (msg !== null) {
      const data = JSON.parse(msg.content.toString());
      const userId = data.user_id;

      console.log(`Deleting todos for user ${userId}`);
      await deleteTodosByUser(userId);
      channel.ack(msg);
    }
  });
}
