import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGradiantoDeepOceanIJTheme;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import java.awt.*;
import java.awt.dnd.DropTarget;
import java.awt.event.*;
import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class UI extends JFrame implements ActionListener {

    private final JTextArea textArea;
    private final JMenu menuFile, menuEdit, menuFind, menuAbout,menuRun;
    private final JMenuItem newFile, openFile, saveFile, close, clearFile, quickFind,
            aboutMe, aboutSoftware,cut,wordWrap,copy,paste,runCode,selectAll,customize;
    private final JMenuBar menuBar;
    private final JToolBar mainToolbar;
    JButton newButton, openButton, saveButton, clearButton, quickButton, aboutMeButton, aboutButton, closeButton, boldButton, italicButton;
    private final Action selectAllAction;

    private final JComboBox<String> stheme;
    private boolean edit = false;
    private String savefilePath;

    private JComboBox fontSize;
    private final JFrame frame;

    //setup icons - Bold and Italic
    private final ImageIcon boldIcon = new ImageIcon(UI.class.getResource("icons/bold.png"));
    private final ImageIcon italicIcon = new ImageIcon("icons/italic.png");

    // setup icons - File Menu
    private final ImageIcon newIcon = new ImageIcon(UI.class.getResource("icons/new.png"));
    private final ImageIcon openIcon = new ImageIcon(UI.class.getResource("icons/open.png"));
    private final ImageIcon saveIcon = new ImageIcon(UI.class.getResource("icons/save.png"));
    private final ImageIcon closeIcon = new ImageIcon(UI.class.getResource("icons/close.png"));

    // setup icons - Edit Menu
    private final ImageIcon clearIcon = new ImageIcon(UI.class.getResource("icons/clear.png"));
    private final ImageIcon cutIcon = new ImageIcon(UI.class.getResource("icons/cut.png"));
    private final ImageIcon copyIcon = new ImageIcon(UI.class.getResource("icons/copy.png"));
    private final ImageIcon pasteIcon = new ImageIcon(UI.class.getResource("icons/paste.png"));
    private final ImageIcon selectAllIcon = new ImageIcon(UI.class.getResource("icons/selectall.png"));
    private final ImageIcon wordwrapIcon = new ImageIcon(UI.class.getResource("icons/wordwrap.png"));

    // setup icons - Search Menu
    private final ImageIcon searchIcon = new ImageIcon(UI.class.getResource("icons/search.png"));

    // setup icons - Help Menu
    private final ImageIcon aboutMeIcon = new ImageIcon(UI.class.getResource("icons/about_me.png"));
    private final ImageIcon aboutIcon = new ImageIcon(UI.class.getResource("icons/about.png"));

    //    private SupportedKeywords kw = new SupportedKeywords();
//    private HighlightText languageHighlighter = new HighlightText(Color.GRAY);


    public UI(){
        frame = this;
        setSize(800,500);
        setTitle("Untitled | "+sjte.NAME);

        // Set the default close operation (exit when it gets closed)
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // center the frame on the monitor
        setLocationRelativeTo(null);

        // adding textArea
        textArea = new JTextArea("",0,0);
        this.add(textArea);
        textArea.setLineWrap(true);

        // adding scrollBar
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setWrapStyleWord(true);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        getContentPane().setLayout(new BorderLayout()); // the BorderLayout bit makes it fill it automatically
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane);
        getContentPane().add(panel);

        // Set the Menus
        menuFile = new JMenu("File");
        menuEdit = new JMenu("Edit");
        menuFind = new JMenu("Search");
        menuAbout = new JMenu("About");
        menuRun = new JMenu(("Run"));
//        menuSettings = new JMenu("Settings");

        // Set the Items Menu
        newFile = new JMenuItem("New", newIcon);
        openFile = new JMenuItem("Open", openIcon);
        saveFile = new JMenuItem("Save", saveIcon);
        close = new JMenuItem("Quit", closeIcon);
        clearFile = new JMenuItem("Clear", clearIcon);
        quickFind = new JMenuItem("Quick", searchIcon);
        aboutMe = new JMenuItem("About Me", aboutMeIcon);
        aboutSoftware = new JMenuItem("About Software", aboutIcon);
        runCode = new JMenuItem("Run Code");
        customize = new JMenuItem("Customize");
//        theme1 = new JMenuItem("theme1");


        menuBar = new JMenuBar();
        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuFind);
        menuBar.add(menuAbout);
        menuBar.add(menuRun);
//        menuBar.add(menuSettings);

        // Set Actions:
        selectAllAction = new SelectAllAction("Select All", clearIcon, "Select all text", new Integer(KeyEvent.VK_A),textArea);

        this.setJMenuBar(menuBar);

        // New File
        newFile.addActionListener(this);  // Adding an action listener (so we know when it's been clicked).
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK)); // Set a keyboard shortcut
        menuFile.add(newFile); // Adding the file menu

        // Open File
        openFile.addActionListener(this);
        openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        menuFile.add(openFile);

        // Save File
        saveFile.addActionListener(this);
        saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        menuFile.add(saveFile);

        // close file
        close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
        close.addActionListener(this);
        menuFile.add(close);

        // run code
        runCode.addActionListener(this);
        menuRun.add(runCode);

        // customizing theme

        String s1[] = { "Light", "Dark","Purple","Blue"};

        stheme = new JComboBox(s1);
        stheme.addActionListener(this);


//        // settings
//        customize.addActionListener(this);
//        menuSettings.add(customize);

//        theme1.addActionListener(this);
//        customize.add(theme1);

        // Select All Text
        selectAll = new JMenuItem(selectAllAction);
        selectAll.setText("Select All");
        selectAll.setIcon(selectAllIcon);
        selectAll.setToolTipText("Select All");
        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
        menuEdit.add(selectAll);

        // Clear File (Code)
        clearFile.addActionListener(this);
        clearFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.CTRL_MASK));
        menuEdit.add(clearFile);

        // Cut Text
        cut = new JMenuItem(new DefaultEditorKit.CutAction());
        cut.setText("Cut");
        cut.setIcon(cutIcon);
        cut.setToolTipText("Cut");
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
        menuEdit.add(cut);

        // WordWrap
        wordWrap = new JMenuItem();
        wordWrap.setText("Word Wrap");
        wordWrap.setIcon(wordwrapIcon);
        wordWrap.setToolTipText("Word Wrap");
        wordWrap.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
        menuEdit.add(wordWrap);

        /* CODE FOR WORD WRAP OPERATION
         * BY DEFAULT WORD WRAPPING IS ENABLED.
         */
        wordWrap.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                // If wrapping is false then after clicking on menuitem the word wrapping will be enabled
                /* Setting word wrapping to true */
                // else  if wrapping is true then after clicking on menuitem the word wrapping will be disabled
                /* Setting word wrapping to false */
                textArea.setLineWrap(!textArea.getLineWrap());
            }
        });

        // Set an higlighter to the JTextArea
        textArea.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                setTitle("Untitled | " + sjte.NAME + "     [ Length: " + textArea.getText().length()
                        + "    Lines: " + (textArea.getText() + "|").split("\n").length
                        + "    Words: " + textArea.getText().trim().split("\\s+").length + " ]");
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                edit = true;
//                languageHighlighter.highLight(textArea, kw.getCppKeywords());
//                languageHighlighter.highLight(textArea, kw.getJavaKeywords());
            }
        });

        // Copy Text
        copy = new JMenuItem(new DefaultEditorKit.CopyAction());
        copy.setText("Copy");
        copy.setIcon(copyIcon);
        copy.setToolTipText("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        menuEdit.add(copy);

        // Paste Text
        paste = new JMenuItem(new DefaultEditorKit.PasteAction());
        paste.setText("Paste");
        paste.setIcon(pasteIcon);
        paste.setToolTipText("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
        menuEdit.add(paste);

        // Find Word
        quickFind.addActionListener(this);
        quickFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
        menuFind.add(quickFind);

        // About Me
        aboutMe.addActionListener(this);
        aboutMe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        menuAbout.add(aboutMe);

        // About Software
        aboutSoftware.addActionListener(this);
        aboutSoftware.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
        menuAbout.add(aboutSoftware);

        // adding mainToolBar
        mainToolbar = new JToolBar();
        this.add(mainToolbar, BorderLayout.SOUTH);

        // newButton
        newButton = new JButton(newIcon);
        newButton.setToolTipText("New");
        newButton.addActionListener(this);
        mainToolbar.add(newButton);
        mainToolbar.addSeparator();

        //openButton
        openButton = new JButton(openIcon);
        openButton.setToolTipText("Open");
        openButton.addActionListener(this);
        mainToolbar.add(openButton);
        mainToolbar.addSeparator();

        //saveButton
        saveButton = new JButton(saveIcon);
        saveButton.setToolTipText("Save");
        saveButton.addActionListener(this);
        mainToolbar.add(saveButton);
        mainToolbar.addSeparator();

        //clearButton
        clearButton = new JButton(clearIcon);
        clearButton.setToolTipText("Clear All");
        clearButton.addActionListener(this);
        mainToolbar.add(clearButton);
        mainToolbar.addSeparator();

        //quickButton
        quickButton = new JButton(searchIcon);
        quickButton.setToolTipText("Quick Search");
        quickButton.addActionListener(this);
        mainToolbar.add(quickButton);
        mainToolbar.addSeparator();

        //aboutMebutton
        aboutMeButton = new JButton(aboutMeIcon);
        aboutMeButton.setToolTipText("About Me");
        aboutMeButton.addActionListener(this);
        mainToolbar.add(aboutMeButton);
        mainToolbar.addSeparator();

        //aboutButton
        aboutButton = new JButton(aboutIcon);
        aboutButton.setToolTipText("About NotePad PH");
        aboutButton.addActionListener(this);
        mainToolbar.add(aboutButton);
        mainToolbar.addSeparator();

        //closeButton
        closeButton = new JButton(closeIcon);
        closeButton.setToolTipText("Quit");
        closeButton.addActionListener(this);
        mainToolbar.add(closeButton);
        mainToolbar.addSeparator();

        //boldButton
        boldButton = new JButton(boldIcon);
        boldButton.setToolTipText("Bold");
        boldButton.addActionListener(this);
        mainToolbar.add(boldButton);
        mainToolbar.addSeparator();

        //italicsButton
        italicButton = new JButton(italicIcon);
        italicButton.setToolTipText("Italic");
        italicButton.addActionListener(this);
        mainToolbar.add(italicButton);
        mainToolbar.addSeparator();

        stheme.setMaximumSize(new Dimension(70,30));
        mainToolbar.add(stheme);
        mainToolbar.addSeparator();

        fontSize = new JComboBox<Integer>();

        for (int i = 5; i <= 100; i++) {
            fontSize.addItem(i);
        }
        fontSize.setMaximumSize(new Dimension(70, 30));
        fontSize.setToolTipText("Font Size");
        mainToolbar.add(fontSize);

        fontSize.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                String sizeValue = fontSize.getSelectedItem().toString();
                int sizeOfFont = Integer.parseInt(sizeValue);
                String fontFamily = textArea.getFont().getFamily();

                Font font1 = new Font(fontFamily, Font.PLAIN, sizeOfFont);
                textArea.setFont(font1);
            }
        });
    }

    // this function is to override default closing when we press red close button
    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            if (edit) {
                Object[] options = {"Save and exit", "No Save and exit", "Return"};
                int n = JOptionPane.showOptionDialog(this, "Do you want to save the file ?", "Question",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                if (n == 0) {// save and exit
                    saveFile();
                    this.dispose();// dispose all resources and close the application
                } else if (n == 1) {// no save and exit
                    this.dispose();// dispose all resources and close the application
                }
            } else {
                System.exit(99);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // if run is pressed
        if(e.getSource()==runCode){
            if(edit){
                saveFile();
            }
//            new runPage("hello from runpage");
            try {
                runProcess("javac "+savefilePath);
                new runPage(runProcess("java "+savefilePath).get(0) + runProcess("java "+savefilePath).get(1));
            } catch (Exception ea) {
                ea.printStackTrace();
            }

        }

//         if source of event if close option
        if(e.getSource()==close || e.getSource()==closeButton){
            if(edit){
                Object[] options={"Save and Exit","Dont Save and Exit","Return"};

                int n = JOptionPane.showOptionDialog(this, "Do you want to save the file ?", "Question",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

                // save and exit
                if(n==0){
                    saveFile();
                    this.dispose();
                }
                else if(n==1){
                    this.dispose();
                }
            }
            else {
                this.dispose();
            }
        }
        else if(e.getSource()==newFile || e.getSource()==newButton){
            if (edit) {
                Object[] options = {"Save", "No Save", "Return"};
                int n = JOptionPane.showOptionDialog(this, "Do you want to save the file at first ?", "Question",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
                if (n == 0) {// save
                    saveFile();
                    edit = false;
                } else if (n == 1) {
                    edit = false;
                    FEdit.clear(textArea);
                }
            } else {
                FEdit.clear(textArea);
            }
        }
        else if(e.getSource()==openButton || e.getSource()==openFile){
            JFileChooser open=new JFileChooser(); // opens file box
            if( !(textArea.getText().equals("")) ) {
                saveFile();
            }

            int option = open.showOpenDialog(this); // get the option that the user selected (approve or cancel)

            if (option == JFileChooser.APPROVE_OPTION) {
                FEdit.clear(textArea); // clear the TextArea before applying the file contents
                try {
                    File openFile = open.getSelectedFile();
                    setTitle(openFile.getName() + " | " + sjte.NAME);
                    Scanner scan = new Scanner(new FileReader(openFile.getPath()));
                    savefilePath = openFile.getPath();
                    while (scan.hasNext()) {
                        textArea.append(scan.nextLine() + "\n");
                    }

                } catch (Exception ex) { // catch any exceptions, and...
                    // ...write to the debug console
                    System.err.println(ex.getMessage());
                }
            }
        }
        else if(e.getSource()==saveButton || e.getSource()==saveFile){
            saveFile();
        }
        else if(e.getSource()==boldButton){
            if (textArea.getFont().getStyle() == Font.BOLD) {
                textArea.setFont(textArea.getFont().deriveFont(Font.PLAIN));
            } else {
                textArea.setFont(textArea.getFont().deriveFont(Font.BOLD));
            }
        }
        else if(e.getSource()==italicButton){
            if (textArea.getFont().getStyle() == Font.ITALIC) {
                textArea.setFont(textArea.getFont().deriveFont(Font.PLAIN));
            } else {
                textArea.setFont(textArea.getFont().deriveFont(Font.ITALIC));
            }
        }
        if(e.getSource()==clearButton || e.getSource()==clearFile){
            Object[] options = {"Yes", "No"};
            int n = JOptionPane.showOptionDialog(this, "Are you sure to clear the text Area ?", "Question",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
            if (n == 0) {// clear
                FEdit.clear(textArea);
            }
        }
        if(e.getSource()==quickButton || e.getSource()==quickFind){
            new Find(textArea);
        }
        if (e.getSource() == aboutMe || e.getSource() == aboutMeButton) {
            new About(this).me();
        } // About Software
        if (e.getSource() == aboutSoftware || e.getSource() == aboutButton) {
            new About(this).software();
        }
        if(e.getSource() == stheme){
            String p = (String) stheme.getSelectedItem();
            try {
                if(p=="Light"){
                    UIManager.setLookAndFeel( new FlatLightLaf() );
                }
                else if(p=="Dark"){
                    UIManager.setLookAndFeel( new FlatDarkLaf());
                }
                else if(p=="Purple") {
                    UIManager.setLookAndFeel(new FlatDarkPurpleIJTheme());
                }
                else if(p=="Blue"){
                    UIManager.setLookAndFeel(new FlatGradiantoDeepOceanIJTheme());
                }
            } catch( Exception ex ) {
                System.err.println( "Failed to initialize LaF" );
            }
            SwingUtilities.updateComponentTreeUI(frame);
        }
    }

    public void saveFile() {
        // Open a file chooser
        JFileChooser fileChoose = new JFileChooser();
        // Open the file, only this time we call
        int option = fileChoose.showSaveDialog(this);

        /*
         * ShowSaveDialog instead of showOpenDialog if the user clicked OK
         * (and not cancel)
         */
        if (option == JFileChooser.APPROVE_OPTION) {
            try {
                File openFile = fileChoose.getSelectedFile();
                setTitle(openFile.getName() + " | " + sjte.NAME);

                BufferedWriter out = new BufferedWriter(new FileWriter(openFile.getPath()));
                savefilePath = openFile.getPath();
                out.write(textArea.getText());
                out.close();

                edit = false;
            } catch (Exception ex) { // again, catch any exceptions and...
                // ...write to the debug console
                System.err.println(ex.getMessage());
            }
        }
//        System.out.println(savefilePath);
    }

    class SelectAllAction extends AbstractAction {

        /**
         * Used for Select All function
         */
        private static final long serialVersionUID = 1L;

        public SelectAllAction(String text, ImageIcon icon, String desc, Integer mnemonic, final JTextArea textArea) {
            super(text, icon);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(MNEMONIC_KEY, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            textArea.selectAll();
        }
    }

    public class FEdit {
        public static void clear(JTextArea textArea) {
            textArea.setText("");
        }
    }

    // for running the program
    public static String printOutputLines(String cmd, InputStream ins) throws Exception {
        String line = null;
        String ans="";
        BufferedReader in = new BufferedReader(
                new InputStreamReader(ins));
        while ((line = in.readLine()) != null) {
            ans+=line+"\n";
        }
        return ans;
    }

    public static ArrayList<String> runProcess(String command) throws Exception {
        Process pro = Runtime.getRuntime().exec(command);
        pro.waitFor();
        String a =  printOutputLines(command,pro.getInputStream());
        String b= printOutputLines(command,pro.getErrorStream());
        ArrayList<String> arr =new ArrayList<String>();
        arr.add(a);
        arr.add(b);
        return arr;
    }
}