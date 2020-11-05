/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//di nguoi lai voi quyen loi 
package Controller;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import user.user;

/**
 *
 * @author van hieu
 * 
 * 
 * 
 */

public class server implements Command {

    public ServerSocket ssRegister;
    public ServerSocket ssLogin;
    public ServerSocket control;
    public ServerSocket session;
    public static Hashtable<String,loginHandler> hash;
    public server() throws IOException, ClassNotFoundException {
        control = new ServerSocket(1002);
        session = new ServerSocket(1003);
        ssLogin = new ServerSocket(1001);
        ssRegister = new ServerSocket(1004);
            
        while (true) {
            
             listenning();
             
        }   
    }       
            
    private void listenning() throws IOException, FileNotFoundException, ClassNotFoundException {
        System.out.println("Server Listening : ");
        Socket s = control.accept();
        DataInputStream dis = new DataInputStream(s.getInputStream());
        int n = dis.readInt();
        if (n == LOGIN) { 
            
            Thread han = new loginHandler(ssLogin);   
            hash.put(han.getName(), (loginHandler) han);   
            han.start();   
            
        } else {   
            
            Thread re = new registerHandler(ssRegister);
            
        }
    }

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        server server = new server();
        
    }
}
