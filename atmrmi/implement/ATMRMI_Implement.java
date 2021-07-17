
package atmrmi.Implement;

/**
 *
 * @author DELL
 */

import atmrmi.Interface.ATMRMI_Interface;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ATMRMI_Implement extends UnicastRemoteObject implements ATMRMI_Interface {
   
            public ATMRMI_Implement() throws RemoteException {
        super();
    }
    
    

    @Override
    public boolean getEnter(String account, String pin) throws RemoteException {
        
        boolean found=false;
        try{
           int acc = Integer.parseInt(account);
           int pn = Integer.parseInt(pin);
             Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/atm");
             Statement s = con.createStatement();
             ResultSet rs = s.executeQuery("SELECT * FROM ACCOUNT WHERE ACCOUNT="+acc+"AND PIN="+pn);
            
                    if(rs.next()){
                        
                         found=true;
                         }else{
                         found=false;
                         
        }
        
        }catch(Exception e){
            e.printStackTrace();
        }
        return found;
    }

    @Override
    public boolean pinChange(String pin,String a,String p) throws RemoteException {
      boolean ok=false;
      try{
             int pn = Integer.parseInt(pin);
             Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/atm");
             Statement s = con.createStatement();
             s.executeUpdate("UPDATE ACCOUNT SET PIN="+pn+" WHERE PIN="+p+" AND ACCOUNT="+a);
             ok=true;
         }catch(Exception e){
            e.printStackTrace();
        }
        return ok;
    }

    @Override
    public int deposit(String amt,String a,String p) throws RemoteException {
        int bal=0;
      try{
             int amount = Integer.parseInt(amt);
             Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/atm");
             Statement s = con.createStatement();
             ResultSet rs = s.executeQuery("SELECT * FROM ACCOUNT WHERE ACCOUNT="+a+"AND PIN="+p);
             rs.next();
             bal=rs.getInt("balance");
             bal=bal+amount;
             s.executeUpdate("UPDATE ACCOUNT SET BALANCE="+bal+" WHERE ACCOUNT="+a);
            // s.executeUpdate("UPDATE ACCOUNT SET PIN="+pn+" WHERE PIN="+p);
             
         }catch(Exception e){
            e.printStackTrace();
        }  
      return bal;
    }

    @Override
    public int withdraw(String amt,String a,String p) throws RemoteException {
          int bal=0;
      try{
             int amount = Integer.parseInt(amt);
             Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/atm");
             Statement s = con.createStatement();
             ResultSet rs = s.executeQuery("SELECT * FROM ACCOUNT WHERE ACCOUNT="+a+"AND PIN="+p);
             rs.next();
             bal=rs.getInt("balance");
             bal=bal-amount;
             s.executeUpdate("UPDATE ACCOUNT SET BALANCE="+bal+" WHERE ACCOUNT="+a);
            // s.executeUpdate("UPDATE ACCOUNT SET PIN="+pn+" WHERE PIN="+p);
             
         }catch(Exception e){
            e.printStackTrace();
        }  
      return bal;
    }

    @Override
    public int checkBalance(String a,String p) throws RemoteException {
       int bal=0;
      try{
           
             Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/atm");
             Statement s = con.createStatement();
              ResultSet rs = s.executeQuery("SELECT * FROM ACCOUNT WHERE ACCOUNT="+a+"AND PIN="+p);
             rs.next();
             bal=rs.getInt("balance");
             
            // s.executeUpdate("UPDATE ACCOUNT SET PIN="+pn+" WHERE PIN="+p);
             
         }catch(Exception e){
            e.printStackTrace();
        }  
      return bal;
    }


    
}
