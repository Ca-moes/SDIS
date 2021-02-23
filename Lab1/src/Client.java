import java.io.*;
import java.util.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println(args[0]); //host
        System.out.println(args[1]); //port
        System.out.println(args[2]); //oper   - "register" / "lookup"
        System.out.println(args[3]); //opnd*  - "<DNS name> [<IP address>] for register | <DNS name> for lookup"
        System.out.println(args[4]); //opnd2* - <IP address> for register

        DatagramSocket socket = new DatagramSocket(3001);

        String command = new String(args[2] + " " + args[3] + " " + args[4]);
        byte[] buf = command.getBytes();
        InetAddress address = InetAddress.getByName(args[0]);
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, Integer.parseInt(args[1]));
        socket.send(packet);

    }
}
