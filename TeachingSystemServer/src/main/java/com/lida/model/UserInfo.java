
package com.lida.model;

/**
 *
 * @author Lida
 */
public class UserInfo {
    private static String userName;
    private static String userPassword;

    public UserInfo(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }


    public static String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
        
}
