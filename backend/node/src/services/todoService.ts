import { PrismaClient } from '@prisma/client';

const prisma = new PrismaClient();

export async function deleteTodosByUser(
  userId: number | bigint
): Promise<void> {
  try {
    await prisma.todos.deleteMany({
      where: {
        user_id: userId,
      },
    });

    console.log(`Deleted todos for user ${userId}`);
  } catch (error) {
    console.error(`Error deleting todos for user ${userId}:`, error);
  }
}
