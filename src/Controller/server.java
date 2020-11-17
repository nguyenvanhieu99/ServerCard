/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//di nguoi lai voi quyen loi 
package Controller;

import DAO.UserDAO;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import model.userSendObject;
import model.serverSendObject;



/**
 *
 * @author van hieu
 *
 *
 *
 */
public class server implements Command {
    public ServerSocket control;
    public ServerSocket session;
    public Socket s;
    static ArrayList<loginHandler> arr = new ArrayList();
    static int dem=0;
    public server() throws IOException, ClassNotFoundException, FileNotFoundException, SQLException {
        control = new ServerSocket(1002);

        while (true) {
            
            listenning();

        }
    }

    private void listenning() throws IOException, FileNotFoundException, ClassNotFoundException, SQLException {
        System.out.println("Server Listening : ");
         s = control.accept();
        log("co ket noi");
        ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
        log("mo luong inout");
       
        
        userSendObject term =new userSendObject();
        //term=ReceiveData();
        
        term=(userSendObject) ois.readObject();
        int n =term.getCommand();
         User o = (User) term.getObject();
        if (n == LOGIN) {
            if (o instanceof User) {
                log(o.getUserName()+" : "+o.getPassWord());
                if (checkUser(o)) {
                    serverSendObject d=new serverSendObject(LOGIN, true, o);
                     ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                     oos.writeObject(d);
                    //sendData(d);
                    log("da gui");
                    Status sta=new Status(o.getUserName(),"wating", dem);
                    loginHandler han;
                    han = new loginHandler(ois, oos, n, s,sta);
                    
                    Thread ds=new Thread(han);
                    
                    ds.start();
                    
                    arr.add(han);
                    dem++;
                    
                } else {
                    
                     serverSendObject d=new serverSendObject(LOGIN, false, o);
                    sendData(d);
                }
            }

        } else if(n==Command.REGISTER) {
            if(register(o)){
                serverSendObject d=new serverSendObject(REGISTER, true, o);
                     ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                     oos.writeObject(d);
            }else {
                serverSendObject d=new serverSendObject(REGISTER, false, o);
                     ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                     oos.writeObject(d);
            }
                
            registerHandler re= new registerHandler(s);
        }
    }

    public userSendObject ReceiveData(){
         userSendObject om = new userSendObject();
        try {
           
            
            ObjectInputStream ois;
            ois = new ObjectInputStream(s.getInputStream());
            om = (userSendObject) ois.readObject();
            return om;
        } catch (IOException ex) {
            Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return om;
    }
    
    public void sendData(serverSendObject orm){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(orm);
        } catch (IOException ex) {
            Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private boolean checkUser(User user) throws ClassNotFoundException, SQLException {
        UserDAO contr = new UserDAO();
        
        if(contr.checkLogin(user)){
            return true;
        }
        else {
            return false;
        }        
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static ArrayList<loginHandler> getArr() {
        return arr;
    }
    public  void log(String s){
        System.out.println(s);
    }

    private boolean register(User o) throws ClassNotFoundException, SQLException {
        UserDAO udao =new UserDAO();
        
        if(udao.register(o)) return true;
        return false;
                
    }
    
    
    
    
}
