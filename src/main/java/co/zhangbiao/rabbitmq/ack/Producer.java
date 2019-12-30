package co.zhangbiao.rabbitmq.ack;

import java.util.HashMap;

import com.rabbitmq.client.AMQP;
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
		String exchangeName = "test_ack_exchange";
		String routingKey = "ack.save";

		// 5、投递消息
		for (int i = 0; i < 5; i++) {
			HashMap<String, Object> headers = new HashMap<String, Object>();
			headers.put("num", i);

			AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().deliveryMode(2)
					.contentEncoding("UTF-8").headers(headers).build();
			String msg = "Hello RabbitMQ Message ack " + i;
			channel.basicPublish(exchangeName, routingKey, properties, msg.getBytes());
		}

	}

}
