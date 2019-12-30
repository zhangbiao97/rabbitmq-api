package co.zhangbiao.rabbitmq.ttl;

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
		String exchangeName = "test_ttl_exchange";
		String routingKey = "ttl.save";
		
		// 队列过期时间
		HashMap<String, Object> headers = new HashMap<String,Object>();
		headers.put("x-message-ttl", 10000);

		AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().deliveryMode(2)
				.contentEncoding("UTF-8")
				// 设置消息过期时间
				//.expiration("10000")
				.headers(headers)
				.build();
		// 5、投递消息
		for (int i = 0; i < 5; i++) {
			String msg = "Hello RabbitMQ Message ack " + i;
			channel.basicPublish(exchangeName, routingKey, properties, msg.getBytes());
		}

	}

}
