package co.zhangbiao.rabbitmq.returnlistener;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;

public class Consumer {

	public static void main(String[] args) throws Exception {

		// 1、创建ConnectionFactory并进行配置
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("123.56.9.64");
		connectionFactory.setPort(5672);
		connectionFactory.setVirtualHost("/");

		// 2、通过ConnectionFactory创建Connection
		Connection connection = connectionFactory.newConnection();

		// 3、创建Channel
		Channel channel = connection.createChannel();

		// 4、声明交换机和队列并进行绑定
		String exchangeName = "test_return_exchange";
		String routingKey = "return.save";
		String queueName = "test_return_queue";
		channel.exchangeDeclare(exchangeName, "direct", true, false, null);
		channel.queueDeclare(queueName, true, false, false, null);
		channel.queueBind(queueName, exchangeName, routingKey);

		// 5、创建消费者
		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(queueName, consumer);

		// 6、接收消息
		while (true) {
			Delivery delivery = consumer.nextDelivery();
			String body = new String(delivery.getBody());
			System.out.println("消费端：" + body);
		}

	}

}
