import java.io.*;
import java.util.*;
import java.net.*;

// cls && javac Server.java && java Server 3000
public class Server {
    public static HashMap<String, InetAddress> records;

    public static void main(String[] args) throws IOException {
        records = initialRecords();

        System.out.println(args[0]); // port number
        records.forEach((key,value)->{
            System.out.println(key + " - " + value);
        });

        DatagramSocket socket = new DatagramSocket(Integer.parseInt(args[0]));
        byte[] buf;

        while(true){
            buf = new byte[256];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

            System.out.println("Server: " + clean(new String(buf)));

            switch (new String(buf).split(" ")[0]){ // type of request
                case "lookup":
                    buf = lookup(clean(new String(buf))).getBytes();
                    break;
                case "register":
                    buf = register(clean(new String(buf))).getBytes();
                    break;
            }

            // Resposta
            InetAddress returnAddress = packet.getAddress();
            int returnPort = packet.getPort();
            packet = new DatagramPacket(buf, buf.length, returnAddress, returnPort);
            socket.send(packet);
        }

    }

    public static HashMap<String, InetAddress> initialRecords() throws UnknownHostException {
        HashMap<String, InetAddress> records = new HashMap<String, InetAddress>();

        records.put("test1", InetAddress.getByName("1.2.3.4")); // https://stackoverflow.com/a/5571816/14643464
        records.put("test2", InetAddress.getByName("2.3.4.5"));
        records.put("test3", InetAddress.getByName("3.4.5.6"));

        return records;
    }

    public static String clean(String request){
        return request.trim();
    }

    public static String lookup(String name){
        String[] results = name.split(" ");
        InetAddress address = records.get(results[1]);
        return address != null ? address.toString() : "ERROR";
    }

    public static String register(String data) throws UnknownHostException {
        String[] parts = data.split(" ", 3);
        InetAddress returnValue = records.put(parts[1], InetAddress.getByName(parts[2]));
        return returnValue != null ? "ERROR" : parts[1] + " " + parts[2];
    }
}
