package RMI1;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface RmiInterface extends Remote{
    void do_put(byte[] data, String dirPath, int fileLength) throws RemoteException ;
    int getFileSize(String filePath) throws RemoteException;
    byte[] do_get(String pathOnServer) throws RemoteException;
    String[] do_dir(String pathOnServer) throws RemoteException;
    String do_mkdir(String pathOnServer) throws RemoteException;
    String do_rmdir(String pathOnServer) throws RemoteException;
    void do_shutdown() throws RemoteException;


}