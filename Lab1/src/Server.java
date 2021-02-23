import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.net.*;

public class Server {

    public static void main(String[] args) throws IOException {
        Boolean received = false;
        HashMap<String, InetAddress> records = initialRecords();

        System.out.println(args[0]); // port number
        records.forEach((key,value)->{
            System.out.println(key + " - " + value);
        });

        DatagramSocket socket = new DatagramSocket(Integer.parseInt(args[0]));

        byte[] buf = new byte[256];

        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        System.out.println(new String(buf));
    }

    public static HashMap<String, InetAddress> initialRecords() throws UnknownHostException {
        HashMap<String, InetAddress> records = new HashMap<String, InetAddress>();

        records.put("teste1", InetAddress.getByName("1.2.3.4")); // https://stackoverflow.com/a/5571816/14643464
        records.put("teste2", InetAddress.getByName("2.3.4.5"));
        records.put("teste3", InetAddress.getByName("3.4.5.6"));

        return records;
    }
}
