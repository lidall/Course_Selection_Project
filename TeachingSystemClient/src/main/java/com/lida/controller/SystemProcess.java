
package com.lida.controller;

import com.lida.model.SystemOperation;
import com.lida.net.Receiver;
import com.lida.view.SelectionOperation;
import java.awt.Robot;

/**
 *
 * @author Lida
 */
public class SystemProcess {
    
    public static void infoDataProcess() throws Exception{
        
        SystemOperation.infoDataIn();                
        SelectionOperation.selectionMain();          // Change User Interface
    }
    
    public static void dataUploadProcess(String dataIn) throws Exception{
        SystemOperation.courseDataUpload(dataIn);    
    }
    
    public static void checkProcess(String courseID) throws Exception{
        Receiver receiver = new Receiver("Queue3");      //The Queue Name Selection No.3
            Thread receiverThread = new Thread(receiver);
            receiverThread.start();                      // Start the receive Thread 
            SystemOperation.checkDataIn(courseID);       // Remote call the server to start transmit the data     
            System.out.println("Waiting for msg");        
            new Robot().delay(100);                      // Waiting for 100ms and then shows the interface
           
    }
    
}
