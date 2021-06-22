import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class SSLClient {
    public static void main(String[] args) {
        System.out.println("again");

        int port = Integer.parseInt(args[1]);

        SSLSocket socket = null;
        SSLSocketFactory ssf = null;

        ssf = (SSLSocketFactory) SSLSocketFactory.getDefault();

        try {
            socket = (SSLSocket) ssf.createSocket(args[0], Integer.parseInt(args[1]));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println(args[2] + args[3] + args[4]);
        }
        catch( IOException e) {
            System.out.println("Server - Failed to create SSLServerSocket");
            System.out.println(e.getMessage());
            return;
        }

    }
}
