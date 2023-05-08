package RMI2;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface PeerClientInterface extends Remote{
    IndexServerInterface peerServ() throws RemoteException;
    void indexRegister() throws RemoteException;
    String userDirec() throws RemoteException;
    String getPeerPort() throws RemoteException;
    boolean sendData(String filename, byte[] data, int len) throws RemoteException;
    String getName() throws RemoteException;
    String[] getFiles() throws RemoteException;

}