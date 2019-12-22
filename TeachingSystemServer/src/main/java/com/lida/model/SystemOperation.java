
package com.lida.model;

import com.lida.controller.SystemInitial;
import com.lida.net.Sender;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Lida
 */
public class SystemOperation {
    
    
    public static void firstDataProcess()
        {
            Connection conn = SystemInitial.getConn();
            String sql = "select * from CourseInfo";
            PreparedStatement pstmt;
            try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            Sender sender = new Sender("Queue");

            DataInfo dataInfo = new DataInfo();

            while (rs.next())
            {
                String courseName = rs.getString(1);
                String courseID = rs.getString(2);
                System.out.println("course name: "+courseName+" course ID: "+ courseID);
                dataInfo.setItemName("@"+courseName);
                dataInfo.setItemID(courseID);
                dataInfo.printItem();
                sender.sendMessage(dataInfo);
            }
            } catch (Exception e) {
                 e.printStackTrace();
            }


            sql = "select * from StudentInfo";
            try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            Sender sender = new Sender("Queue");

            DataInfo dataInfo = new DataInfo();

            while (rs.next())
            {
                String studentName = rs.getString(1);
                String studentID = rs.getString(2);
                System.out.println("course name: "+studentName+" course ID: "+ studentID);
                dataInfo.setItemName("#"+studentName);
                dataInfo.setItemID(studentID);
                dataInfo.printItem();
                sender.sendMessage(dataInfo);
            }
            } catch (Exception e) {
                 e.printStackTrace();
            }

        }
    
    
    public static void checkDataProcess(String courseID){
        Connection conn = SystemInitial.getConn();
        String sql = "select * from " + courseID;
        PreparedStatement pstmt;
        try {
        pstmt = (PreparedStatement)conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        Sender sender = new Sender("Queue3");

        DataInfo dataInfo = new DataInfo();

        while (rs.next())
        {
            String studentName = rs.getString(1);
            String studentID = rs.getString(2);
            System.out.println("check student name: "+studentName+" check student ID: "+ studentID);
            dataInfo.setItemName("!"+studentName);
            dataInfo.setItemID(studentID);
            dataInfo.printItem();
            sender.sendMessage(dataInfo);
        }


        } catch (Exception e) {
             e.printStackTrace();
        }
    }
    
}
