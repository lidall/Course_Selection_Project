
package com.lida.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.lida.controller.SystemInitial;
/**
 *
 * @author Lida
 */
public class DatabaseFunction {
    
    //Used for register
    public static int dataInsert(UserInfo userInfo) {
        
        Connection conn = SystemInitial.getConn();
        int i = 0;
        String sql = "insert into UserInfo (userName,userPassword) values(?,?)";
        PreparedStatement pstmt;
        try {

            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, userInfo.getUserName());
            pstmt.setString(2, userInfo.getUserPassword());
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
}
    
    
    //Used for student selection
    public static int uploadDataInsert(UserInfo userInfo,String courseID) {

        Connection conn = SystemInitial.getConn();
        int i = 0;
        String sql = "insert into "+courseID+" (studentName,studentID) values(?,?)";
        PreparedStatement pstmt;
        try {

            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, userInfo.getUserName());
            pstmt.setString(2, userInfo.getUserPassword());
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }   
    
 
    public static Integer dataBaseGetAll() {
        Connection conn = SystemInitial.getConn();
        String sql = "select * from UserInfo";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            int col = rs.getMetaData().getColumnCount();
            System.out.println("============================");
            while (rs.next()) {
                for (int i = 1; i <= col; i++) {
                    System.out.print(rs.getString(i) + "\t");
                    if ((i == 2) && (rs.getString(i).length() < 8)) {
                        System.out.print("\t");
                    }
                 }
                System.out.println("");
            }
                System.out.println("============================");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
