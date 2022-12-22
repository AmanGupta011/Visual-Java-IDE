import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class About {

    private final JFrame frame;
    private final JPanel panel;
    private String contentText;
    private final JLabel text;

    public About(UI ui) {
        panel = new JPanel(new FlowLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        frame = new JFrame();

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });


        frame.setVisible(true);
        frame.setSize(500,300);
        frame.setLocationRelativeTo(ui);
        text = new JLabel();
    }

    public void me() {
        frame.setTitle("About Me - " + sjte.NAME);

        contentText =
                "<html><body><p>" +
                        "Authors: Aman Gupta<br/>" +
                        "Avadhut Savale<br/" +
                        "Ujjwal Kumar Pandey<br/><br/>" +
                        "Contact us at: " +
                        "<a href='mailto:" + sjte.AUTHOR_EMAIL + "?subject=About the NotePad PH Software'>" + sjte.AUTHOR_EMAIL + "</a>" +
                        "<br /><br />" +
                        "</p></body></html>";

        text.setText(contentText);
        panel.add(text);
        frame.add(panel);
    }

    public void software() {
        frame.setTitle("About Software - " + sjte.NAME);

        contentText =
                "<html><body><p>" +
                        "Name: " + sjte.NAME + "<br />" +
                        "Version: " + sjte.VERSION +
                        "</p></body></html>";

        text.setText(contentText);
        panel.add(text);
        frame.add(panel);
    }
}