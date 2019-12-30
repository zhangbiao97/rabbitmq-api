package co.zhangbiao.rabbitmq.consumer;

import java.io.IOException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class CustomizerConsumer extends DefaultConsumer {

	public CustomizerConsumer(Channel channel) {
		super(channel);
	}

	@Override
	public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
			throws IOException {
		// TODO Auto-generated method stub
		System.err.println("-----------------handleDelivery------------------");
		System.err.println("consumerTag:" + consumerTag);
		System.err.println("envelope:" + envelope);
		System.err.println("properties:" + properties);
		System.err.println("body:" + new String(body));
	}

}
