/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael
 */
public class UserMapper {
    String rowsFromDB = "";
    public boolean createUser(String username, String password){
        rowsFromDB = "";
        try {
//            String sql = "SELECT username, password FROM usertable WHERE username = 'admin'";
            String sql = "INSERT INTO usertable (username, password) VALUES(?, ?)";
            Connection con = DB.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            int rs = pstmt.executeUpdate();
            if(rs>0){
                  System.out.println("success");
                  return true;
            }else{
                 System.out.println("stuck somewhere");
                 return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
//    public User getUser() {}
    @SuppressWarnings("empty-statement")
    public List<User> getAllUsers() {
        rowsFromDB = "";    
        try {
            String sql = "SELECT COUNT(*) FROM usertable";
            Connection con = DB.getConnection();
            PreparedStatement pstmtLoggedIn = con.prepareStatement(sql);
            ResultSet rs = pstmtLoggedIn.executeQuery();
            if(rs.next()){
                rowsFromDB = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<User> User = new ArrayList<>(Integer.parseInt(rowsFromDB));
        try {
            String sql = "SELECT * FROM usertable";
            Connection con = DB.getConnection();
            PreparedStatement pstmtLoggedIn = con.prepareStatement(sql);
            ResultSet rs = pstmtLoggedIn.executeQuery();            
            for (int i = 0; i < Integer.parseInt(rowsFromDB); i++) {
                rs.next();
                String userFromDB = rs.getString("username");
                String passwordFromDB = rs.getString("password");
                //User.set(i, );
                System.out.println("User: "+userFromDB+" - "+passwordFromDB);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return User;
    }
//    public void deleteUser() {}
    public void editUser(User user) {}
    public boolean authenticateUser(String username, String password){
        try {
//            String sql = "SELECT username, password FROM usertable WHERE username = 'admin'";
            String sql = "SELECT username, password FROM usertable WHERE username = ?";
            Connection con = DB.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                String passwordFromDB = rs.getString("password");
                if(passwordFromDB.equals(password)){
                    return true;
                }
            }else{
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return false;
    }
//    public static void main(String[] args){
//        UserMapper um = new UserMapper();
//        boolean isAuthenticated = um.authenticateUser("admin", "admin123");
//        if(isAuthenticated){
//            System.out.println("Yes das funktioniert!! :-D");
//        }else{
//            System.out.println("Das funktioniert nicht!! :-(");
//        }
//    }

//    public static void main(String[] args){
//        try {
//            String sql = "SELECT * FROM usertable";
//            Connection con = DB.getConnection();
//            PreparedStatement pstmtLoggedIn = con.prepareStatement(sql);
//            ResultSet rs = pstmtLoggedIn.executeQuery();
//            while(rs.next()){
//                String userFromDB = rs.getString("username");
//                String passwordFromDB = rs.getString("password");
//                System.out.println("User: "+userFromDB+" - "+passwordFromDB);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

//    public static void main(String[] args){
//        try {
//            String sql = "SELECT COUNT(*) FROM usertable";
//            Connection con = DB.getConnection();
//            PreparedStatement pstmtLoggedIn = con.prepareStatement(sql);
//            ResultSet rs = pstmtLoggedIn.executeQuery();
//            if(rs.next()){
//                String rowsFromDB = rs.getString(1);
//                System.out.println("Rows: "+rowsFromDB);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//    }
    public static void main(String[] args){
    String rowsFromDB = "";    
        try {
            String sql = "SELECT COUNT(*) FROM usertable";
            Connection con = DB.getConnection();
            PreparedStatement pstmtLoggedIn = con.prepareStatement(sql);
            ResultSet rs = pstmtLoggedIn.executeQuery();
            if(rs.next()){
                rowsFromDB = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            String sql = "SELECT * FROM usertable";
            Connection con = DB.getConnection();
            PreparedStatement pstmtLoggedIn = con.prepareStatement(sql);
            ResultSet rs = pstmtLoggedIn.executeQuery();
            for (int i = 0; i < Integer.parseInt(rowsFromDB); i++) {
                rs.next();
                String userFromDB = rs.getString("username");
                String passwordFromDB = rs.getString("password");
                System.out.println("User: "+userFromDB+" - "+passwordFromDB);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
