package co.zhangbiao.rabbitmq.returnlistener;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ReturnListener;

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
		String exchangeName = "test_return_exchange";
		String routingKey = "return.save";
		String routingKeyError = "return.error";

		// 5、投递消息
		String msg = "Hello RabbitMQ Return Message!";
		channel.basicPublish(exchangeName, routingKeyError, true, false, null, msg.getBytes());

		// 6、添加Return Listener
		channel.addReturnListener(new ReturnListener() {
			@Override
			public void handleReturn(int replyCode, String replyText, String exchange, String routingKey,
					AMQP.BasicProperties properties, byte[] body) throws IOException {
				System.err.println("----------------handle return-------------------");
				System.err.println("replyCode:" + replyCode);
				System.err.println("replyText:" + replyText);
				System.err.println("exchange:" + exchange);
				System.err.println("routingKey:" + routingKey);
				System.err.println("properties:" + properties);
				System.err.println("body:" + new String(body));
			}
		});
	}

}
