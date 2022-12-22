import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

public class sjte {

    private static final long serialVersionUID = 1L;
    public final static String AUTHOR_EMAIL = "iit2021201@iiita.ac.in";
    public final static String NAME = "JAVAX";
    public final static double VERSION = 1.0;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        new UI().setVisible(true);
    }
}
