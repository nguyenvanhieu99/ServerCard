/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class UserDAO extends DAO{
    public boolean checkLogin(User u) throws ClassNotFoundException, SQLException{
        String checklogin = " Select * user where userName = ? and password = ?";
        PreparedStatement p = getConnect().prepareStatement(checklogin);
        p.setString(1, u.getUserName());
        p.setString(2, u.getPassWord());
        ResultSet rs = p.executeQuery();
        
        if(rs.next())  return true;
        else return false;
    }
}
