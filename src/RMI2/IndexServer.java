package RMI2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;


public class IndexServer extends UnicastRemoteObject implements IndexServerInterface{

    private static final long serialVersionUID = 1L;
    //Create the Array
    private ArrayList<PeerClientInterface> peers = new ArrayList<>();
    public IndexServer() throws RemoteException{
        super();
    }

    //build array first
    public void peerArray() throws RemoteException {
        for (int a=0; a<peers.size(); a++ ) {
            System.out.println("Name: "+ peers.get(a).getName());
            System.out.println("Files: ");
            String[] peerfiles = peers.get(a).getFiles();
            //within the peer, what files
            for (int i=0; i<peerfiles.length; i++) {
                System.out.println("\n"+peerfiles[i]);
            }
        }
    }



    //search for file
    public synchronized PeerClientInterface[] searchIndex(String file, String peerInitial) throws RemoteException {
        Boolean find = false;
        PeerClientInterface[] peer = new PeerClientInterface[peers.size()];
        int count = 0;
        for (int l=0; l<peers.size(); l++ ) {
            String[] peersString = peers.get(l).getFiles();
            for (int a=0; a<peersString.length; a++) {
                if (file.equals(peersString[a])) {
                    find = true;
                    peer[count] = peers.get(l);
                    count++;
                }
            }
        }
        if(find) {
            System.out.println("FileFound in" + peerInitial + "system.");
            return peer;
        } else
            System.out.println("File not found");
        return peer;
    }

    //Need to have synchronzed for multiple uploads
    public synchronized String addtoIndex(PeerClientInterface newPeer) throws RemoteException {

        //add new peer to peers Array
        peers.add(newPeer);

        String addedFiles = "";
        String[] files = newPeer.getFiles();
        //Loop through and add all files, display
        for (int i=0; i<files.length; i++){
            addedFiles += "\n"+files[i];
        }
        System.out.println("Peer Registered with Index.");
        peerArray();
        return newPeer.getName() + ":" + addedFiles +" = added";
    }

    //Add changes to the index.
    public boolean indexUpdate(PeerClientInterface newPeer) throws RemoteException {

        for (int a=0; a<peers.size(); a++ ) {
            if(newPeer.getName().equals(peers.get(a).getName())){
                //have to remove first. Error returned otherwise.
                peers.remove(a);
                peers.add(newPeer);
            }
        }
        System.out.println("Server updated");
        peerArray();
        return true;
    }

}