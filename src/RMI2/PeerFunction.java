package RMI2;

import java.io.File;
import java.io.FileInputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class PeerFunction extends UnicastRemoteObject implements PeerFunctionInterface {


    private static final long serialVersionUID = 1L;
    //private String file = "";

    protected PeerFunction() throws RemoteException{
        super();
    }
    @Override
    public String getName() {
        return "allen";
    }

    public synchronized boolean login(PeerClientInterface c, String file) {
        //Sending File...

        try{
            File f1 = new File(file);
            FileInputStream in=new FileInputStream(f1);
            byte [] mydata=new byte[1024*1024];
            int mylen=in.read(mydata);
            while(mylen>0){
                if(c.sendData(f1.getName(), mydata, mylen)){
                    System.out.println("File Sent!");
                    mylen=in.read(mydata);
                }
                else{
                    System.out.println("File not sent?");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }


}