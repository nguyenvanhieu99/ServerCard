/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servercard;

import Controller.server;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author van hieu
 */
public class ServerCard {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            server ser=new server();
        } catch (IOException ex) {
            Logger.getLogger(ServerCard.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerCard.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServerCard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
