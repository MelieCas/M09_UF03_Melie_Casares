import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static final int PORT = 9999;
    public static final String HOST = "localhost";
    public ServerSocket server;
    public Socket socket;

    private ObjectOutputStream output;
    private ObjectInputStream input;

    public Socket connectar() {
        try {
            server = new ServerSocket(PORT);

            socket = server.accept();
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
        

            return socket;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void tancarConnexio(Socket socket) {
        try {
            socket.close();
            server.close();
            output.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enviarFitxers() {
       
        String nom;
        try {
            nom = input.readObject().toString();
            Fitxer fitxer = new Fitxer(nom);
            output.writeObject(fitxer.getContingut());
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
            
        
            
            
            
            

       
    }

    public static void main(String[] args) {
        Servidor servidor = new Servidor();
        servidor.connectar();
        servidor.enviarFitxers();
        servidor.tancarConnexio(servidor.socket);
    }
}
