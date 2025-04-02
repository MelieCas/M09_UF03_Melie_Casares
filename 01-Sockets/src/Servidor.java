import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static final int PORT = 7777;
    public static final String HOST = "localhost";
    
    private ServerSocket srvSocket;
    private Socket clientSocket;

    public void connecta() {
        try {
            srvSocket = new ServerSocket(PORT);
            
            clientSocket = srvSocket.accept();
            
            
            
        } catch (IOException e) {
    
        }
    }

    public void repDades() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String text = reader.readLine();
            while (text != null) {
                System.out.printf("Rebut: %s%n", text);
                text = reader.readLine();
            }
            
            
            
            
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tanca() {
        try {
            srvSocket.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Servidor servidor = new Servidor();
        System.out.printf("Servidor en marxa a %s:%d%n", HOST, PORT);
        servidor.connecta();
        System.out.printf("Client connectat %s%n", servidor.clientSocket.getLocalAddress());
        servidor.repDades();
        servidor.tanca();

    }
}