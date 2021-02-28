import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class RMIServer implements Methods{
    public static HashMap<String, InetAddress> records;

    public static void main(String[] args) {
        try {
            RMIServer obj = new RMIServer();
            Methods stub = (Methods) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Methods", stub);

            System.err.println("Server Ready");
        } catch (RemoteException | AlreadyBoundException e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }


    @Override
    public String register(String name) {
        String[] results = name.split(" ");
        InetAddress address = records.get(results[1]);
        return address != null ? address.toString() : "ERROR";
    }

    @Override
    public String lookup(String data) {
        try {
            String[] parts = data.split(" ", 3);
            InetAddress returnValue = null;
            returnValue = records.put(parts[1], InetAddress.getByName(parts[2]));
            return returnValue != null ? "ERROR" : parts[1] + " " + parts[2];
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
}
