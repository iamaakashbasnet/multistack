import pika
import json
from django.db.models.signals import post_delete
from django.contrib.auth.models import User
from django.dispatch import receiver

@receiver(post_delete, sender=User)
def user_deleted_handler(sender, instance, **kwargs):
    connection = pika.BlockingConnection(pika.ConnectionParameters('localhost'))
    channel = connection.channel()

    channel.queue_declare(queue='user.deleted')

    message = json.dumps({'user_id': instance.id})
    channel.basic_publish(exchange='', routing_key='user.deleted', body=message)

    connection.close()
