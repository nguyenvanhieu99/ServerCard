/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import model.User;
import model.userIO;

/**
 *
 * @author van hieu
 */
public class registerHandler extends Thread  {
    public  registerHandler(ServerSocket ss) throws IOException{
        register(ss);
       
    }


    
    public static void register(ServerSocket ss)   throws IOException{
        Socket s = ss.accept();
        Thread a=new Thread((Runnable) s);
        DataInputStream dis = new DataInputStream(s.getInputStream());
        String userName = dis.readUTF();
        String PassWord = dis.readUTF();
        User us = new User(userName, PassWord);
        new userIO().WriteFile(us);
        System.out.println(us.toString() + " GHI VAO FILE ");
    }
}
