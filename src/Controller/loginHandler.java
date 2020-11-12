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
import model.serverSendObject;
import model.userIO;
import model.table;
import model.userSendObject;

/**
 *
 * @author van hieu
 *
 */
public class loginHandler implements Runnable {

    static Hashtable<String, table> listtable;
    ObjectInputStream dis;
    ObjectOutputStream oos;
    Status status;
    public int dem;
    Socket s;
    public boolean islogin;

    public loginHandler(ObjectInputStream dis, ObjectOutputStream oos, int dem, Socket s, Status sta) {
        this.dis = dis;
        this.oos = oos;
        this.dem = dem;
        this.s = s;
        this.islogin = true;
        this.status = sta;
    }

    @Override
    public void run() {
        String received = null;
        while (true) {
            try {
                int so = -1;

                Object obj = dis.readObject();
                userSendObject uso = (userSendObject) obj;
                so = uso.getCommand();

                log(received);

                if (so == Command.CHALLENGE) {
                    try {

                        ObjectInputStream ob = new ObjectInputStream(s.getInputStream());

                    } catch (IOException ex) {

                        Logger.getLogger(loginHandler.class.getName()).log(Level.SEVERE, null, ex);

                    }

                } else if (so == Command.CREATTABLE) {
                    table ta = new table();
                    ta.add(status.getName(), this);
                    listtable.put(status.getName(), ta);

                } else if (so == Command.JOINTABLE) {
                     
                } else if (so == Command.LOGOUT) {
                    this.islogin = false;
                    try {
                        this.s.close();
                    } catch (IOException ex) {
                        Logger.getLogger(loginHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                } else if (so == Command.LOGINDONE) {
                    ArrayList<Object> table = getAlltable();
                    serverSendObject term = new serverSendObject(so, islogin, table, table);
                    receiveObject(term);
                    ArrayList<Object> all = getAllclient();
                    receiveObject(term);

                } else if (so == Command.RANK) {
                    getRank();

                }
            } catch (IOException ex) {
                Logger.getLogger(loginHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(loginHandler.class.getName()).log(Level.SEVERE, null, ex);
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

    public ArrayList<Object> getAllclient() {
        return null;
    }

    public ArrayList<Object> getAlltable() {
        return null;
    }

    public ArrayList<Object> getRank() {
        return null;
    }

    public void joinTable() {

    }

    public void play() {

    }

    public void creatTable() {

    }
    
    public void sendObject(Object o) {
        try {
            oos.writeObject(o);
            
        } catch (IOException ex) {
            Logger.getLogger(loginHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Object receiveObject(Object o) {
        try {
            Object ob = dis.readObject();
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
