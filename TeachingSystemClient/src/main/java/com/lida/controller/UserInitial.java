
package com.lida.controller;

import static com.lida.startup.SystemClient.URL;
import static com.lida.startup.SystemClient.SERVER_IP;
import com.lida.view.SetServerAddr;
public class UserInitial {

    public static void initialInter(){
         SetServerAddr.initialMain();   // Start the initial program
    }
    
    
    public static void userInitial(){   // Setting the server IP address
        URL ="rmi://"+SERVER_IP+":9999/rmi.server.controller.ServiceImpl";
    }
    
}
