
package com.lida.net;

/**
 *
 * @author Lida
 */
import com.lida.startup.SystemClient;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class BaseConnector {
    protected Channel channel;
    protected Connection connection;
    protected String queueName;
    public BaseConnector(String queueName) throws Exception{
        this.queueName = queueName;
        //Open the connection and create the channel
        ConnectionFactory factory = new ConnectionFactory();
        //Setting the ip address for the server
        factory.setHost(SystemClient.SERVER_IP);
        //Setting the Username Password & Virtual host name
        factory.setUsername("harry");
        factory.setPassword("abc");
        factory.setVirtualHost("/");
        factory.setPort(5672);
        //Creating the connection
        connection = factory.newConnection();
        //Creating the channel
        channel = connection.createChannel();
        //Declare the queue
        //Set the properties of the queue ! The first parameter is the queue name. 
        //The second parameter is whether to create a persistent queue
        //The third is to decide whether to create a dedicated queue,
        //The fourth parameter is to determine whether automatically delete the queue
        //The fifth parameter is the other attributes (structure parameters)
        channel.queueDeclare(queueName, false, false, false, null);
    }
}
