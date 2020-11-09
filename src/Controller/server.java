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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import model.User;

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
    static Hashtable<String, loginHandler> hash = new Hashtable<>();
    static int dem=0;
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
        ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

        int n = dis.readInt();
        if (n == LOGIN) {
            Object o = ois.readObject();
            if (o instanceof User) {
                User user = (User) o;
                if (checkUser(user)) {
                    oos.writeObject("ok");
                    loginHandler han = new loginHandler(dis, oos, n, s);
                    Thread ds=new Thread(han);
                    ds.start();
                    hash.put(user.getUserName(), (loginHandler) han);
                    dem++;
                } else {
                    oos.writeObject("false");
                }
            }

        } else {
            Thread re = new registerHandler(ssRegister);
        }
    }
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        server server = new server();

    }

    private boolean checkUser(User user) {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
