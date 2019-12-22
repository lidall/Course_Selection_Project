
package com.lida.startup;

import com.lida.controller.SystemInitial;
/**
 *
 * @author Lida
 */
public class SystemServer {


public static String SERVER_IP;

public static void main(String[] args) throws Exception{ 
    SystemInitial.interfaceInitial();
    
} 

public static String getCTX(String originalCTX,String firstSplit,String secondSplit){
        String resultCTX = originalCTX.substring(originalCTX.lastIndexOf(firstSplit), 
        originalCTX.lastIndexOf(secondSplit));
        resultCTX = resultCTX.substring(1,resultCTX.length());
        return resultCTX;
    }


}  

