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
        Socket s = control.accept();
        ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
        userSendObject term=(userSendObject)ois.readObject();
        int n =term.getCommand();
        if (n == LOGIN) {
            Object o = term.getObject();
            if (o instanceof User) {
                User user = (User) o;
                if (checkUser(user)) {
                    
                    oos.writeObject("ok");
                    Status sta=new Status(user.getUserName(),"wating", dem);
                    loginHandler han = new loginHandler(ois, oos, n, s,sta);
                    
                    Thread ds=new Thread(han);
                    
                    ds.start();
                    
                    arr.add(han);
                    dem++;
                    
                } else {
                    oos.writeObject("false");
                }
            }

        } else {
//            Thread re = new registerHandler(ssRegister);
        }
    }
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        try {
            server server = new server();
        } catch (SQLException ex) {
            Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private boolean checkUser(User user) throws ClassNotFoundException, SQLException {
        UserDAO contr = new UserDAO();
        User u = null;
        if(contr.checkLogin(u)){
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

    
    
    
    
}
