package co.zhangbiao.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {

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

		// 4、声明
		String exchangeName = "test_consumer_exchange";
		String routingKey = "consumer.save";

		// 5、投递消息
		String msg = "Hello RabbitMQ Customize Consumer!";
		channel.basicPublish(exchangeName, routingKey, null, msg.getBytes());

		// 6、关闭连接
		channel.close();
		connection.close();
	}

}
