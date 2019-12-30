package co.zhangbiao.rabbitmq.limit;

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
		String exchangeName = "test_qos_exchange";
		String routingKey = "qos.save";

		// 5、投递消息
		String msg = "Hello RabbitMQ Message qos!";
		for (int i = 0; i < 5; i++) {
			channel.basicPublish(exchangeName, routingKey, null, msg.getBytes());
		}

	}

}
