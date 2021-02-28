import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {
    public static void main(String[] args) {
        String host = (args.length < 1) ? null : args[0];

        try {
            Registry registry = LocateRegistry.getRegistry();
            Methods stub = (Methods) registry.lookup("Methods");
            // String response = stub.sayHello();
            System.out.println("response: " + response);
        } catch (RemoteException | NotBoundException e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
