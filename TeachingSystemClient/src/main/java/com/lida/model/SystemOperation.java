
package com.lida.model;

import com.lida.common.RMIInterface;
import static com.lida.startup.SystemClient.URL;
import java.rmi.Naming;
import com.lida.net.Receiver;
import com.lida.net.Sender;
import static com.lida.startup.SystemClient.getCTX;
/**
 *
 * @author Lida
 */
public class SystemOperation {
    
    static String[] dataUpload;
    
    public static void infoDataIn() throws Exception{
        RMIInterface userOperation=(RMIInterface)Naming.lookup(URL);
        userOperation.firstDataExchange();
        Receiver receiver = new Receiver("Queue");
        Thread receiverThread = new Thread(receiver);
        receiverThread.start();
     
    }
    
    public static void courseDataUpload(String dataIn) throws Exception{
        
        RMIInterface userOperation=(RMIInterface)Naming.lookup(URL);
        
        Sender sender = new Sender("Queue2");
        DataInfo dataInfo = new DataInfo();
        
        dataUpload =  dataIn.split("\n"); 
       
        
        for(int i = 0;i<dataUpload.length;i++){
            
            String dataTemp = "(" + dataUpload[i] + ")";
            String studentInfo = getCTX(dataTemp,"(",">");
            String courseID = getCTX(dataTemp,"-",")");
            System.out.println("Student name & ID: "+studentInfo+" course ID: "+ courseID);
            dataInfo.setItemName(studentInfo);
            dataInfo.setItemID(courseID);
            dataInfo.printItem();
            sender.sendMessage(dataInfo);
        }
        
        userOperation.resultUpload();
        
    }
    
    
    public static void checkDataIn(String courseID) throws Exception{
        RMIInterface userOperation=(RMIInterface)Naming.lookup(URL);
        userOperation.checkExchange(courseID);
    }
    
}
