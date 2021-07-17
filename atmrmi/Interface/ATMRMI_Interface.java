
package atmrmi.Interface;

import java.rmi.*;

/**
 *
 * @author Utkarsha
 */
public interface ATMRMI_Interface extends Remote {
  public boolean getEnter(String account,String pin) throws RemoteException; 
  public boolean pinChange(String pin,String a,String p) throws RemoteException;
  public int deposit(String amt,String a,String p) throws RemoteException;
  public int withdraw(String amt,String a,String p) throws RemoteException;
  public int checkBalance(String acc,String pin) throws RemoteException;
  
}
