import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Server {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));

        int portNumber = Integer.parseInt(args[0]);

        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;
            inputLine = in.readLine();
            out.println(inputLine);

            /*
            https://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html
            https://docs.oracle.com/javase/tutorial/networking/sockets/examples/EchoServer.java
             */
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
