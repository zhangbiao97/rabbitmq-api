package co.zhangbiao.rabbitmq.limit;

import java.io.IOException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class MyConsumer extends DefaultConsumer {

	private Channel channel;

	public MyConsumer(Channel channel) {
		super(channel);
		this.channel = channel;
	}

	@Override
	public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
			throws IOException {
		System.err.println("------------------handleDelivery---------------");
		System.err.println("consumerTag:" + consumerTag);
		System.err.println("envelope:" + envelope);
		System.err.println("properties:" + properties);
		System.err.println("body:" + new String(body));
		// 手动ack
		channel.basicAck(envelope.getDeliveryTag(), false);
	}

}
