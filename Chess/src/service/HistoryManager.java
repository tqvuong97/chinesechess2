/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author King
 */
public class HistoryManager {
    public static Connection openConnection()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/ChineseChess";
            Connection con = DriverManager.getConnection(url, "root", "");
            System.out.println(con);
            return con;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
            
    }
    public void saveWin(int id1,int id2,String result){
        Connection con=null;
        try {      
        con= openConnection();
        String sql="insert into history values(null,?,?,?,now())";
        PreparedStatement pstm= con.prepareStatement(sql);
       // pstm.setString(1, u.getId());
        pstm.setInt(1, id1);
        pstm.setInt(3, id2);
        pstm.setString(2, result);
        //pstm.setString(4, "NOW()");
        
        pstm.executeUpdate();
        
        } catch (SQLException ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     
}
