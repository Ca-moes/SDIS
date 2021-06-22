import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

public class Client {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));

        try {
            Socket socket = new Socket(args[0], Integer.parseInt(args[1]));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println(args[2] + args[3] + args[4]);
            System.out.println("echo: " + in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
