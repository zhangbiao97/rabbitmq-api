package co.zhangbiao.rabbitmq.dlx;

import com.rabbitmq.client.AMQP.BasicProperties;
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
		String exchangeName = "test_dlx_exchange";
		String routingKey = "dlx.save";
		BasicProperties properties = new BasicProperties().builder().contentEncoding("UTF-8").expiration("10000")
				.build();

		// 5、投递消息
		String msg = "Hello RabbitMQ Customize Consumer!";
		channel.basicPublish(exchangeName, routingKey, properties, msg.getBytes());

		// 6、关闭连接
		channel.close();
		connection.close();
	}

}
