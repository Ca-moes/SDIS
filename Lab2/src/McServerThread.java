import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.*;
import java.util.Timer;
import java.util.TimerTask;

public class McServerThread extends Thread{
    private String mcastAddr, mcastPort, srvcAddr, srvcPort;
    private DatagramSocket socket;
    private DatagramPacket packet;

    public McServerThread(String mcastAddr, String mcastPort, String srvcAddr, String arvcPort) throws SocketException, UnknownHostException {
        this.mcastAddr = mcastAddr;
        this.mcastPort = mcastPort;
        this.srvcAddr = srvcAddr;
        this.srvcPort = arvcPort;
        this.socket = new DatagramSocket(3000);

        InetAddress group = InetAddress.getByName(mcastAddr);
        String message = srvcAddr + " " + srvcPort;
        byte[] buf = message.getBytes();
        packet = new DatagramPacket(buf, buf.length, group, Integer.parseInt(mcastPort));
    }

    public void run(){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    socket.send(packet);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("multicast: " + mcastAddr + " " + mcastPort + ": " + srvcAddr + " " + srvcPort);

            }
        };

        Timer temporizador = new Timer();
        temporizador.schedule(task, 0, 1000);
    }
}
