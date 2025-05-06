import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    
    public final String DIR_ARRIBADA = "/tmp";
    private Socket socket;
    public ObjectOutputStream output;
    public ObjectInputStream input;

    public void connectar() {
        try {
            socket = new Socket(Servidor.HOST, Servidor.PORT);

            input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream(socket.getOutputStream());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void rebreFitxers() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            output.writeObject(reader.readLine());
            input.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }   

    public void tancarConnexio() {
        try {
            socket.close();
            input.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        

    }

    public static void main(String[] args) {
        Client client = new Client();
        client.connectar();
        client.rebreFitxers();
        client.tancarConnexio();
    }
    
}
