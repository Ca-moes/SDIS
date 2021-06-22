import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SSLServer {
    private int Port;
    private final SSLServerSocket s;

    public static void main(String[] args) {
        System.out.println("Test");

        int port = Integer.parseInt(args[0]);

        SSLServerSocket s = null;
        SSLServerSocketFactory ssf = null;

        ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();

        try {
            s = (SSLServerSocket) ssf.createServerSocket(port);
            SSLSocket clientSocket = (SSLSocket) s.accept();

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;
            inputLine = in.readLine();
            out.println(inputLine);
        }
        catch( IOException e) {
            System.out.println("Server - Failed to create SSLServerSocket");
            System.out.println(e.getMessage());
            return;
        }

    }

    // TODO https://gist.github.com/artem-smotrakov/bd14e4bde4d7238f7e5ab12c697a86a3
    private SSLServer(){
        SSLServerSocket socket  =
                (SSLServerSocket) SSLServerSocketFactory.getDefault().createServerSocket(port);
        this.s = socket;
    }

    public SSLServer create(int port) throws IOException {
        SSLServerSocket socket  =
                (SSLServerSocket) SSLServerSocketFactory.getDefault().createServerSocket(port);
        return new SSLServer(socket);
    }
}
