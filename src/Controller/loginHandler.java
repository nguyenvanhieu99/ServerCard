/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
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

    ObjectInputStream dis;
    ObjectOutputStream oos;
    public int dem;
    Socket s;
    public boolean islogin;
    public loginHandler(ObjectInputStream dis, ObjectOutputStream oos, int dem, Socket s) {
        this.dis = dis;
        this.oos = oos;
        this.dem = dem;
        this.s = s;
        this.islogin=true;
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
                try {
                    
                    ObjectInputStream  ob=new ObjectInputStream(s.getInputStream());
                    
                } catch (IOException ex) {
                    
                    Logger.getLogger(loginHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            }
            else if(so==Command.LOGOUT){
                this.islogin=false; 
                try { 
                    this.s.close();
                } catch (IOException ex) {
                    Logger.getLogger(loginHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                    break; 
            }
            else if(so==Command.GETALLTABLE){
                getAlltable();
                getAllclient();
                
                Hashtable<String,loginHandler>k= server.getHash();
                
                
            }
            else if(so==Command.RANK){
                getRank();
            }
        }
        
        
        try { 
            this.dis.close();
             this.oos.close(); 
        } catch (IOException ex) {
            Logger.getLogger(loginHandler.class.getName()).log(Level.SEVERE, null, ex);
            log("khong dong duoc ");
        }
           

    }


    public void getAllclient() {
         
    }

    public void getAlltable() {

    }

    public void getRank() {

    }

    public void joinTable() {

    }
    public void play(){
        
    }
    public void creatTable(){
        
    }
    public void sendObject(Object o){
        try {
            oos.writeObject(o);
        } catch (IOException ex) {
            Logger.getLogger(loginHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Object  receiveObject(Object o){
        try {
            Object ob= dis.readObject();
            return ob;
        } catch (IOException ex) {
            Logger.getLogger(loginHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(loginHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
        
    public void log(String mess) {
        System.out.println("log: " + mess);
    }

}
