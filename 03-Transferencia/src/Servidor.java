import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static final int PORT = 9999;
    public static final String HOST = "localhost";
    public ServerSocket server;
    public Socket socket;

    private ObjectInputStream input;

    public Socket connectar() {
        try {
            server = new ServerSocket(PORT);

            socket = server.accept();

            return socket;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void tancarConnexio(Socket socket) {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enviarFitxers() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String nom = reader.readLine();
            
            Fitxer fitxer = new Fitxer(nom);
            
            
            
            
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        
    }
}
