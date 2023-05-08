package RMI2;


import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;


public class StartPeerClient {
    private static final String Indsrv = "localhost";
    static String arg=null;
    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {

        //take 2 provided port as the arg for peerclientURL below
        arg=args[1];
        String location = "rmi://"+Indsrv+":"+args[0]+"/peerserver";
        IndexServerInterface peerServer = (IndexServerInterface) Naming.lookup(location);
        PeerClient clientserver= new PeerClient(args[2],args[1],peerServer);

        //Better way to start Threads??
        new Thread(new peerfunction()).start();
        new Thread(clientserver).start();
    }
    static class peerfunction implements Runnable
    {
        public void run()
        {
            try {
                String clientloc = "rmi://"+Indsrv+":"+arg+"/clientserver";
                PeerFunctionInterface pcs = new PeerFunction();
                Naming.rebind(clientloc,pcs);
            } catch (RemoteException | MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }
}