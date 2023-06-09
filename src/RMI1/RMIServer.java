package RMI1;

import java.io.Serializable;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class RMIServer implements Serializable {

    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        int portNumber= 0;
        if (args.length==2) {
            if (args[0].equals("start")) {
                portNumber=Integer.parseInt(args[1]);
            }
            else { System.out.println(" 1 Error starting server - USAGE: start <portNumber> ");
                System.exit(1);}
        }
        else {System.out.println(" 2 Error starting server - USAGE: start <portNumber> ");
            System.exit(1);}



        try
        {
            Registry registry = LocateRegistry.createRegistry(portNumber);   //Creates and exports a Registry instance on the local host that accepts requests
            //on the specified port.
            String storage_location = ""; // location of storage
            RMIClient remoteObj =  new RMIClient(storage_location , registry);
            registry.rebind("remoteObj", remoteObj);
            System.out.println("Server is ready at port number: (" + portNumber + ")");

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}