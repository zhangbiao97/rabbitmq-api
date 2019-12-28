package co.zhangbiao.rabbitmq.helloword;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;

public class Consumer {

	public static void main(String[] args) throws Exception {
		// 1、创建一个ConnectionFactory并进行配置
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("123.56.9.64");
		connectionFactory.setPort(5672);
		connectionFactory.setVirtualHost("/");

		// 2、通过ConnectionFactory创建连接
		Connection connection = connectionFactory.newConnection();

		// 3、通过connection创建Channel
		Channel channel = connection.createChannel();

		// 4、声明（创建）一个队列
		String queueName = "test001";
		channel.queueDeclare(queueName, true, false, false, null);

		// 5、创建消费者
		QueueingConsumer consumer = new QueueingConsumer(channel);

		// 6、设置Channel
		channel.basicConsume(queueName, true, consumer);

		while (true) {
			// 7、获取消息
			Delivery delivery = consumer.nextDelivery();
			String body = new String(delivery.getBody());
			System.err.println("消费端：" + body);
		}

	}

}
