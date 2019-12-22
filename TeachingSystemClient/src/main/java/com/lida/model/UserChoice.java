
package com.lida.model;

/**
 *
 * @author Lida
 */
import java.rmi.Naming;
import static com.lida.startup.SystemClient.URL;
import com.lida.common.RMIInterface;

public class UserChoice {

    public static boolean userLogin(String sendCTX) throws Exception{
        boolean breakFLG = false;
        RMIInterface userOperation=(RMIInterface)Naming.lookup(URL); 
        String result=userOperation.loginOperation(sendCTX);
 
        if (result.contains("^")){
            breakFLG = true;
            result = result.replace("^", "");
        }
        System.out.println(result);
        return breakFLG;
    }
    
    public static boolean userRegister(String sendCTX) throws Exception{
        boolean breakFLG = false;
        RMIInterface userOperation=(RMIInterface)Naming.lookup(URL); 
        String result=userOperation.registerOperation(sendCTX);
   
        if (result.contains("^")){
            breakFLG = true;
            result = result.replace("^", "");
        }
        System.out.println(result);
        return breakFLG;
    }
}