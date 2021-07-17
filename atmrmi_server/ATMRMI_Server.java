/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atmrmi_server;

/**
 *
 * @author Utkarsha
 */

import atmrmi.Implement.ATMRMI_Implement;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ATMRMI_Server {
    
    public static void main(String[] args) {
        try {
            Registry reg=LocateRegistry.createRegistry(1086);
            ATMRMI_Implement ai=new ATMRMI_Implement();
            
            //System.setProperty("java.rmi.server.hostname", "127.0.0.1");
            reg.bind("enter", ai);
            
            System.out.println("Server is ready");
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
