import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientXat {
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private String host = ServidorXat.HOST;
    private int port = ServidorXat.PORT;

    public void connecta() {
        try {
            socket = new Socket(host, port);
            
            input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream(socket.getOutputStream());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enviaMissatge(String msg) {
        
    }

    public void tancarClient() {
        try {
            socket.close();
            input.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public static void main(String[] args) {
        ClientXat client = new ClientXat();
        
        client.connecta();
        FilLectorCX fil = new FilLectorCX(client.output);
        
        fil.start();
        
        client.tancarClient();
    }
}