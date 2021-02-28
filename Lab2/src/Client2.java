import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Client2 {
    public static void main(String[] args) throws IOException {
        MulticastSocket multicastSocket = new MulticastSocket(Integer.parseInt(args[1]));
        InetAddress group = InetAddress.getByName(args[0]);
        multicastSocket.joinGroup(group);

        byte[] buf = new byte[256];
        DatagramPacket multicastPacket = new DatagramPacket(buf, buf.length);
        multicastSocket.receive(multicastPacket);

        String received = new String(multicastPacket.getData());
        System.out.println("Received by multicast: " + received);

        String[] stuff = received.split(" ", 2);

        // Received port and address, time to send request

        DatagramSocket socket = new DatagramSocket();
        String command = "invalid";

        if (args[2].equals("register")){
            command = "register " + args[3] + " " + args[4];
        } else if (args[2].equals("lookup")){
            command = "lookup " + args[3];
        } else
            return;

        buf = command.getBytes();
        InetAddress address = InetAddress.getByName(stuff[0]);
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, Integer.parseInt(stuff[1].trim()));
        socket.send(packet);

        buf = new byte[256];
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);

        System.out.println("Client: " + command + " : " + new String(buf));
        return;
    }
}
