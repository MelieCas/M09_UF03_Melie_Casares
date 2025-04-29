import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;

public class FilLectorCX extends Thread {

    private ObjectOutputStream output;

    public FilLectorCX(ObjectOutputStream output) {
        this.output = output;
    }

    @Override
    public void run() {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                String msg = consoleReader.readLine();
                output.writeObject(msg);
                output.flush();
                if (msg.equals(ServidorXat.MSG_SORTIR)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}