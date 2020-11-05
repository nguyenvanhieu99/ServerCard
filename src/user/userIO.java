/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hoa
 */
public class userIO {
    String url = "E:\\PTTK\\login_register\\user.txt";
    public void WriteFile( user us) throws FileNotFoundException, IOException{
        FileOutputStream f = new FileOutputStream(url);
        ObjectOutputStream oos = new ObjectOutputStream(f);
        oos.writeObject(us);
        oos.close();
    }
    public List  <user> ReadFile() throws FileNotFoundException, IOException, ClassNotFoundException{
        user us ;
        ArrayList  userList  = new ArrayList <user> ();
        FileInputStream f = new FileInputStream(url);
        ObjectInputStream ois = new ObjectInputStream(f);
        while( ois.available() > 0 ){
            us = (user) ois.readObject();
            userList.add(us);
        }
        return userList;
    }
}
