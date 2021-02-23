import java.io.*;
import java.util.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        if (args.length != 5 && args.length != 4){
            System.out.println("java Client <host> <port> <oper> <opnd>*");
            return;
        }

        // System.out.println(args[0]); //host
        // System.out.println(args[1]); //port
        // System.out.println(args[2]); //oper   - "register" / "lookup"
        // System.out.println(args[3]); //opnd*  - "<DNS name> [<IP address>] for register | <DNS name> for lookup"
        if ((args.length == 5)) System.out.println(args[4]); //opnd2* - <IP address> for register

        DatagramSocket socket = new DatagramSocket(3001);
        String command = "invalid";

        if (args[2].equals("register")){
            command = new String("register " + args[3] + " " + args[4]);
        } else if (args[2].equals("lookup")){
            command = new String("lookup " + args[3]);
        } else
            return;


        byte[] buf = command.getBytes();
        InetAddress address = InetAddress.getByName(args[0]);
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, Integer.parseInt(args[1]));
        socket.send(packet);

        buf = new byte[256];
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);

        System.out.println("Client: " + command + " : " + new String(buf));
        return;
    }
}
