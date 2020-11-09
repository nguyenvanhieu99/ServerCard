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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import model.userIO;

/**
 *
 * @author van hieu
 *
 */
public class loginHandler implements Runnable {

    DataInputStream dis;
    ObjectOutputStream oos;
    public int dem;
    Socket s;

    public loginHandler(DataInputStream dis, ObjectOutputStream oos, int dem, Socket s) {
        this.dis = dis;
        this.oos = oos;
        this.dem = dem;
        this.s = s;
    }

    @Override
    public void run() {
        String  received = null;
        while (true) {
            try {
                received = dis.readUTF();
            } catch (IOException ex) {
                Logger.getLogger(loginHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            log(received);
            int so=Integer.parseInt(received);
            if(so== Command.CHALLENGE){
                
                
            }
            else if(so==Command.LOGOUT){
                
                
            }
            else if(so==Command.GETALLTABLE){
                
            }
            else if(so==Command.GETALLCLIENT){
                
            }
            else if(so==Command.RANK){
                
            }
            
            
            
        }

    }

//    public loginHandler(ServerSocket ss) throws IOException, FileNotFoundException, ClassNotFoundException {
//        Socket s = ss.accept();
//
//        no = dem;
//        dem++;
//        dis = new DataInputStream(s.getInputStream());
//        oos = new ObjectOutputStream(s.getOutputStream());
//        login(ss);
//        hash.put(no, this);
//        server.hash.put(no, this);
//        getAllclient();
//
//        getAlltable();
//
//
//    }
//    public static User login(ServerSocket ss) throws IOException, FileNotFoundException, ClassNotFoundException {
//        //liston.add(us);
//
//        String userName = dis.readUTF();
//        String passWord = dis.readUTF();
//        System.out.println(userName + "---" + passWord);
//        ArrayList userList = (ArrayList) new userIO().ReadFile();
//
//        User[] users = (User[]) userList.toArray();
//        for (User us : users) {
//            if (us.getUserName().equals(userName) && us.getPassWord().equals(passWord)) {
//
//                oos.writeObject(us);
//                return us;
//            }
//        }
//        System.out.println("Login Fail");
//        return null;
//    }

    public void getAllclient() {

    }

    public void getAlltable() {

    }

    public void getRank() {

    }

    public void joinTable() {

    }

    public void log(String mess) {
        System.out.println("log: " + mess);
    }

}
