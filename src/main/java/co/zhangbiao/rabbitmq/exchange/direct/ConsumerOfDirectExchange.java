package co.zhangbiao.rabbitmq.exchange.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;

public class ConsumerOfDirectExchange {

	public static void main(String[] args) throws Exception {
		// 1、创建ConnectionFactory并进行配置
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("123.56.9.64");
		connectionFactory.setPort(5672);
		connectionFactory.setVirtualHost("/");

		// 2、通过ConnectionFactory创建连接
		Connection connection = connectionFactory.newConnection();

		// 3、创建Channel
		Channel channel = connection.createChannel();

		// 4、声明
		String exchangeName = "test_direct_exchange";
		String exchangeType = "direct";
		String queueName = "test_direct_queue";
		String routingKey = "test.direct";

		// 声明一个交换机
		channel.exchangeDeclare(exchangeName, exchangeType, true, false, false, null);

		// 声明一个队列
		channel.queueDeclare(queueName, false, false, false, null);

		// 建立绑定关系
		channel.queueBind(queueName, exchangeName, routingKey);

		// 创建消费者
		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(queueName, true, consumer);

		// 循环接收消息
		while (true) {
			Delivery delivery = consumer.nextDelivery();
			String body = new String(delivery.getBody());
			System.out.println("消费端：" + body);
		}
	}

}
