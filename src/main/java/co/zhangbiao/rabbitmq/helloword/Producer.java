package co.zhangbiao.rabbitmq.helloword;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {

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
		
		// 4、通过Channel发送数据
		for (int i = 0; i < 5; i++) {
			String msg = "Hello RabbitMQ!";
			channel.basicPublish("", "test001", null, msg.getBytes());
		}
		
		// 5、关闭连接
		channel.close();
		connection.close();
		
	}

}
