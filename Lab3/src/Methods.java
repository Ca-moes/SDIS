import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Methods extends Remote {
    /*String register(String name);
    String lookup(String data);*/
    String sayHello() throws RemoteException;
}
