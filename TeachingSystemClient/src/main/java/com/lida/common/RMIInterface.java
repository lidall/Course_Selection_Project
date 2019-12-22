
package com.lida.common;

/**
 *
 * @author Lida
 */
import java.rmi.Remote;
import java.rmi.RemoteException;

/** 
* This file implement the remote interface which could complete 
* the loging & register & data exchange function
*/

public interface RMIInterface extends Remote{ 

String loginOperation(String userInput)throws RemoteException; 
String registerOperation(String userInput)throws RemoteException;  
void firstDataExchange() throws RemoteException;
void resultUpload() throws RemoteException;
void checkExchange(String courseID) throws RemoteException;



}
