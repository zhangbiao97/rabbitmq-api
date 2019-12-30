package co.zhangbiao.rabbitmq.ack;

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
		System.err.println("------------------consumer message---------------");
		System.err.println("body:" + new String(body));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 手动ack，当num=0时重回队列，否则ack
		if ((Integer) properties.getHeaders().get("num") == 0) {
			channel.basicNack(envelope.getDeliveryTag(), false, true);
		} else {
			channel.basicAck(envelope.getDeliveryTag(), false);
		}
	}

}
