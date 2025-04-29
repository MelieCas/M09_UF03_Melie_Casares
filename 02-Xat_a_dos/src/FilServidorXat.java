import java.io.IOException;
import java.io.ObjectInputStream;

public class FilServidorXat extends Thread {
    public ObjectInputStream input;

    public FilServidorXat(ObjectInputStream input) {
        this.input = input;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String msg = input.readObject().toString();
                System.out.printf("Missatge rebut: %s%n", msg);
                if (msg.equalsIgnoreCase(ServidorXat.MSG_SORTIR)) break;
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
