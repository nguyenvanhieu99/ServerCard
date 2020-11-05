/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;
import user.user;
import user.userIO;

/**
 *
 * @author van hieu
 */
public class loginHandler extends Thread  {
    public static Vector <user> ar;
    public Hashtable<String,user> hash;

    public loginHandler(ServerSocket ss) throws IOException, FileNotFoundException, ClassNotFoundException {
        login(ss);
    }
    
    
    public static user login( ServerSocket ss) throws IOException, FileNotFoundException, ClassNotFoundException {
        //liston.add(us);
        
        Socket s = ss.accept();
        DataInputStream dis = new DataInputStream(s.getInputStream());
        
        String userName = dis.readUTF();
        String passWord = dis.readUTF();
        System.out.println(userName + "---" + passWord);
        ArrayList userList = (ArrayList) new userIO().ReadFile();
        
        user [] users = (user[]) userList.toArray();
        for( user us : users ){
            if( us.getUserName().equals(userName) && us.getPassWord().equals(passWord)){
                ObjectOutputStream oos = new ObjectOutputStream( s.getOutputStream());
                oos.writeObject(us);
                return us;
            }
        }
        System.out.println("Login Fail");
        return null;
    }
    public void getAllclient(){
        
    }
    public void getAlltable(){
        
    }
    public void getRank(){
        
    }
    public void  joinTable(){
        
    }
    
}
