
package com.lida.controller;

import com.lida.model.UserChoice;
import static com.lida.startup.SystemClient.SERVER_IP;
import com.lida.view.LogInterface;
import com.lida.view.RegisterInterface;
import com.lida.view.SetServerAddr;

/**
 *
 * @author Lida
 */


/*
    This class implement all the user process for loging and register
*/
public class UserProcess {
    
    public static void userSetServer(){
        UserInitial.userInitial();
        SetServerAddr.jf.setVisible(false);
        LogInterface.logMain();
        System.out.println("Server ip = " + SERVER_IP);
    }
    
    public static boolean userLoginProcess(String sendCTX) throws Exception{
        
        boolean returnFLG = UserChoice.userLogin(sendCTX);
        return returnFLG;
    }
    
    public static boolean userRegisterProcess(String sendCTX) throws Exception{
        
        boolean returnFLG = UserChoice.userRegister(sendCTX);
        return returnFLG;
    }
    
    public static void registerInterface(){
        RegisterInterface.registerMain();
    }
    
}
