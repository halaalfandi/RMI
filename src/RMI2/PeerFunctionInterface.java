package RMI2;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PeerFunctionInterface extends Remote {
    String getName() throws RemoteException;
    boolean login(PeerClientInterface c, String filename) throws RemoteException;
}