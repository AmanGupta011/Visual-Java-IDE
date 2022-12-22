import javax.swing.*;
import java.awt.*;

public class runPage extends JFrame{
    JTextArea txta;

    public runPage(String output) {
        setSize(500, 500);
        setTitle("Output");
        txta = new JTextArea("" + output, 0, 0);

//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.add(txta);
        txta.setLineWrap(true);

        // adding scrollBar
        JScrollPane scrollPane = new JScrollPane(txta);
        txta.setWrapStyleWord(true);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        getContentPane().setLayout(new BorderLayout()); // the BorderLayout bit makes it fill it automatically
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane);
        getContentPane().add(panel);

        setVisible(true);
    }
}
