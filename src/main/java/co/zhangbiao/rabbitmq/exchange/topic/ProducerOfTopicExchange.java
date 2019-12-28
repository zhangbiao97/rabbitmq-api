package co.zhangbiao.rabbitmq.exchange.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ProducerOfTopicExchange {

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
		String exchangeName = "test_topic_exchange";
		String routingKey1 = "user.save";
		String routingKey2 = "user.update";
		String routingKey3 = "user.delete.abc";

		// 5、发布消息
		String msg = "Hello World RabbitMQ for Topic Exchange Message......";
		channel.basicPublish(exchangeName, routingKey1, null, msg.getBytes());
		channel.basicPublish(exchangeName, routingKey2, null, msg.getBytes());
		channel.basicPublish(exchangeName, routingKey3, null, msg.getBytes());

		// 6、关闭连接
		channel.close();
		connection.close();
	}

}
