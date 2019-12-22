
package com.lida.net;

/**
 *
 * @author Lida
 */
import com.lida.model.DataInfo;
import com.lida.startup.SystemClient;
import java.io.IOException;
import org.apache.commons.lang.SerializationUtils;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.ShutdownSignalException;

public class Receiver extends BaseConnector implements Runnable, Consumer {

    boolean isBroken = false;
    
    public Receiver(String queueName) throws Exception {
        super(queueName);
    }


 
    public void run() {
        errorHandeling();
    }
    //Used when some error happens
    protected void errorHandeling() {
		
        try {
            if (isBroken) {

                    System.out.println(" [x] Something worng ----"+ channel.isOpen());

            }
                channel.basicConsume(queueName, true,this);
        } catch (IOException e) {

            e.printStackTrace();

        } catch (ShutdownSignalException e) {
            try {

                    if (channel.isOpen()) {

                            channel.basicConsume(queueName, true,this);
                            isBroken = false;
                            System.out.println(" [x] Recover sucess ----");
                    }
            } catch (Exception e1) {

                    isBroken = true;
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                    System.out.println(" [x] Thread worng"
                            + "--- ShutdownSignalException-Exception");
            }
        } catch (ConsumerCancelledException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println(" [x] Thread exit"
                        + "----ConsumerCancelledException");
        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println(" [x] 接收线程跳出 ----Exception");

        }

    }
    
    
    // Automatically called once consumer registered
    public void handleConsumeOk(String consumerTag) {
        System.out.println("Consumer "+consumerTag +" registered");
    }
        //The function will be atomatically called once the consumer receive the MSG
        public void handleDelivery(String consumerTag, Envelope env,
                BasicProperties props, byte[] body) throws IOException {
            
        DataInfo dataInfo = (DataInfo)SerializationUtils.deserialize(body);
        
        System.out.println("Message( "
                + "Name: " + dataInfo.getItemName() 
                + " , ID: " + dataInfo.getItemID() 
                + " ) received.");
        dataInfo.printItem();
        
        //For each flag, we have different list to store 
        SystemClient.DATA_TEMP = dataInfo.getItemName() +"-"+ dataInfo.getItemID();
        if(SystemClient.DATA_TEMP.contains("@")){
            SystemClient.DATA_TEMP = SystemClient.DATA_TEMP.replace("@", "");
            SystemClient.COURSE_LIST.add(SystemClient.DATA_TEMP);
            SystemClient.DATA_TEMP = null;
        }
        if(SystemClient.DATA_TEMP.contains("#")){
            SystemClient.DATA_TEMP = SystemClient.DATA_TEMP.replace("#", "");
            SystemClient.STUDENT_LIST.add(SystemClient.DATA_TEMP);
            SystemClient.DATA_TEMP = null;
        }
        if(SystemClient.DATA_TEMP.contains("!")){
            SystemClient.DATA_TEMP = SystemClient.DATA_TEMP.replace("!", "");
            SystemClient.CHECKING_LIST.add(SystemClient.DATA_TEMP);
            SystemClient.DATA_TEMP = null;
        }
      
    }

    public void handleCancelOk(String consumerTag) {
    }
    public void handleCancel(String consumerTag) throws IOException {
    }
    public void handleShutdownSignal(String consumerTag,
            ShutdownSignalException sig) {
    }
    public void handleRecoverOk(String consumerTag) {
    }
}