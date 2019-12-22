
package com.lida.net;

/**
 *
 * @author Lida
 */
import com.lida.startup.SystemServer;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class BaseConnector {
    protected Channel channel;
    protected Connection connection;
    protected String queueName;
    public BaseConnector(String queueName) throws Exception{
        this.queueName = queueName;

        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost(SystemServer.SERVER_IP);
        factory.setUsername("harry");
        factory.setPassword("abc");
        factory.setVirtualHost("/");
        factory.setPort(5672);
       
        connection = factory.newConnection();

        channel = connection.createChannel();

        channel.queueDeclare(queueName, false, false, false, null);
    }
}
