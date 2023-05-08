package RMI2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IndexServerInterface extends Remote {
    boolean indexUpdate(PeerClientInterface newPeer) throws RemoteException;
    String addtoIndex(PeerClientInterface newPeer) throws RemoteException;
    PeerClientInterface[] searchIndex(String file, String peerInitial) throws RemoteException;
    void peerArray() throws RemoteException;

}