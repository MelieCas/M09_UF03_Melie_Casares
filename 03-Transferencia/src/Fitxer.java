import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;

public class Fitxer {
    private String nom;
    private byte[] contingut;

    public Fitxer(String nom) {
        this.nom = nom;
    }

    public byte[] getContingut() {
        File fitxer = new File(nom);
        if (fitxer.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(fitxer);
                contingut = new byte[(int) fitxer.length()];
                fileInputStream.read(contingut);
                fileInputStream.close();
                return contingut;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            System.out.printf("El fitxer %s no existeix %n", nom);
            return null;
        }
    }
}
