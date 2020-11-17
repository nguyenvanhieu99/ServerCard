///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Controller;
//
//import java.io.DataInputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import model.User;
//import model.userIO;
//import model.userSendObject;
//
///**
// *
// * @author van hieu
// */
//public class registerHandler   {
//    
//    public  registerHandler(Socket s) throws IOException{
//        try {
//            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
//            userSendObject uso=(userSendObject) ois.readObject();
//            User us=(User) uso.getObject();
//            userDAO(user);
//            
//            
//            User us = new User(userName, PassWord);
//            new userIO().WriteFile(us);
//            System.out.println(us.toString() + " GHI VAO FILE ");
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(registerHandler.class.getName()).log(Level.SEVERE, null, ex);
//        }
//       
//    }
//}
