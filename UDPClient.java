import java.io.IOException;
import java.net.*;
import java.net.Inet4Address;

class UDPClient extends Thread {
    //private String IP="194.149.135.49";
    //  private int port=9753;
    private int serverPorta;
    //private String indeks="192063";
    private String poraka;
    private String ImeServer;
    private DatagramSocket socket;
    private InetAddress IPServer;
    private byte[] buffer;
    private DatagramPacket packet;

    UDPClient(String new_imeServer, int new_serverPorta, String new_poraka) {
        this.ImeServer = new_imeServer;
        this.serverPorta = new_serverPorta;
        this.poraka = new_poraka;

        try {
            this.socket = new DatagramSocket();
            this.IPServer = InetAddress.getByName(ImeServer);
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        buffer = poraka.getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, this.IPServer, this.serverPorta);

        try {
            socket.send(datagramPacket);

            packet = new DatagramPacket(buffer, buffer.length, IPServer, serverPorta);
            socket.receive(packet);
            System.out.println(new String(datagramPacket.getData(), 0, datagramPacket.getLength()));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }

    public static void main(String[] args) {
        UDPClient klient =new UDPClient("194.149.135.49", 9753, "192063");
        klient.start();
    }

}
