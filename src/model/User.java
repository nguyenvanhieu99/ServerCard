/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
/**
 *
 * @author hoa
 */
import java.io.Serializable;
public class User implements Serializable {
    String userName, PassWord;

    public User(String userName, String PassWord) {
        this.userName = userName;
        this.PassWord = PassWord;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWord(String PassWord) {
        this.PassWord = PassWord;
    }

    @Override
    public String toString() {
        return "user{" + "userName=" + userName + ", PassWord=" + PassWord + '}';
    }
    
}
