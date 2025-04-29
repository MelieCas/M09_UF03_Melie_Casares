import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorXat {
    public static final int PORT = 9999;
    public static final String HOST = "localhost";
    public static final String MSG_SORTIR = "sortir";

    private ServerSocket serverSocket;

    public void iniciarServidor() {
        try {
            serverSocket = new ServerSocket(PORT);

            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pararServidor() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getNom() {}

    public static void main(String[] args) {

        ServidorXat server = new ServidorXat();

        Socket clientSocket;
        try {
            clientSocket = server.serverSocket.accept();
            ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
            
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            FilServidorXat filServer = new FilServidorXat(input);
            filServer.start();

            while (true) {
                String msg = consoleReader.readLine();
                output.writeObject(msg);
                output.flush();
                if (msg.equalsIgnoreCase(MSG_SORTIR)) break;
            }

            clientSocket.close();
            System.out.println("Connexio tancada");

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }
}
