import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private int port = Servidor.PORT;
    private String host = Servidor.HOST;
    private Socket socket;
    private PrintWriter out;

    public void connecta() {
        try {    
            socket = new Socket(host, port);
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tanca() {
        out.close();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void envia(String mg) {
        out.println(mg);
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.connecta();
        System.out.printf("Connectat a servidor en %s:%d%n", client.host, client.port);
        client.envia("Prova d'enviament 1");
        client.envia("Prova d'enviament 2");
        client.envia("Adeu!");
        System.out.println("Enviat al servidor: Prova d'enviament 1");
        System.out.println("Enviat al servidor: Prova d'enviament 2");
        System.out.println("Enviat al servidor: Adeu!");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Prem Enter per tancar el client");
        try {
            if(reader.readLine() == null) {
                client.tanca();
                System.out.println("Client tancat");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    
    }
}
