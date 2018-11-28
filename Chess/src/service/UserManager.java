/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author King
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class UserManager {
    
    
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
    public List<User>  getAllUser(){
        List<User> list = new ArrayList<User>();
            Connection con=null;
            ResultSet rs=null;       
        try {          
            con=openConnection();
            String sql="Select * from user";
            Statement stm=con.createStatement();
            rs=stm.executeQuery(sql);
            
            while(rs.next()){
                String name=rs.getString(2);           
                String pass =rs.getString(6);
                User s= new User( name,pass);
                list.add(s);     
            }    
            con.close();     
        } catch (SQLException ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return list;       
    }
    
    public void validate_login(String username,String password) {
    Connection con=null;
    try{           
       con=openConnection();
       PreparedStatement pst = con.prepareStatement("Select * from user where name=? and pass=?");
       pst.setString(1, username); 
       pst.setString(2, MD5Library.md5(password));
       ResultSet rs = pst.executeQuery();                        
       if(rs.next())   {  
           System.out.println("Ok nha");
           //return true; 
           
       }
       else{
           System.out.println("try again");
           //return false;  
       }          
   }
   catch(Exception e){
       e.printStackTrace();
      // return false;
   }       
}
    
    
    
    public int addUser(User u)
    {
        Connection con=null;
        try {      
        con= openConnection();
        String sql="insert into user values(null,?,0,0,0,?)";
        PreparedStatement pstm= con.prepareStatement(sql);
       // pstm.setString(1, u.getId());
        pstm.setString(1, u.getName());
        pstm.setString(2, MD5Library.md5(u.getPass()));
        
        int row=pstm.executeUpdate();
        return row;
        } catch (SQLException ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
}
