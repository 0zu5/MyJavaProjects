import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TextEditor extends JFrame implements ActionListener {

    JTextArea textArea;
    JScrollPane scrollPane;
    JLabel fontlabel;
    JSpinner fontsizespinner;
    JButton fontcollorbutton;
    JComboBox fontbox;
    JMenuBar menuBar;
    JMenu filemenu;
    JMenuItem openitem;
    JMenuItem saveitem;
    JMenuItem exititem;

    TextEditor(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Text Editor");
        this.setSize(500,500);
        this.setLayout(new FlowLayout());
        this.setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Arial",Font.PLAIN,20));

        scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(450,450));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        fontlabel = new JLabel("Font: ");

        fontsizespinner = new JSpinner();
        fontsizespinner.setPreferredSize(new Dimension(50,25));
        fontsizespinner.setValue(20);
        fontsizespinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                textArea.setFont(new Font(textArea.getFont().getFamily(),Font.PLAIN,(int) fontsizespinner.getValue()));
            }
        });


        fontcollorbutton = new JButton("Color");
        fontcollorbutton.addActionListener(this);

        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        fontbox = new JComboBox(fonts);
        fontbox.addActionListener(this);
        fontbox.setSelectedItem("Arial");

        menuBar = new JMenuBar();
        filemenu = new JMenu("File");
        openitem = new JMenuItem("Open");
        saveitem = new JMenuItem("Save");
        exititem = new JMenuItem("Exit");

        openitem.addActionListener(this);
        saveitem.addActionListener(this);
        exititem.addActionListener(this);

        filemenu.add(openitem);
        filemenu.add(saveitem);
        filemenu.add(exititem);
        menuBar.add(filemenu);


        this.setJMenuBar(menuBar);
        this.add(fontlabel);
        this.add(fontsizespinner);
        this.add(fontcollorbutton);
        this.add(fontbox);
        this.add(scrollPane);
        this.setVisible(true);

    }








    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==fontcollorbutton){
            JColorChooser colorChooser = new JColorChooser();

            Color color = colorChooser.showDialog(null,"choose color",Color.black);

            textArea.setForeground(color);
        }
        if (e.getSource()==fontbox){
            textArea.setFont(new Font((String)fontbox.getSelectedItem(),Font.PLAIN,textArea.getFont().getSize()));
        }

        if (e.getSource()==openitem){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
            fileChooser.setFileFilter(filter);

            int response = fileChooser.showOpenDialog(null);
            if (response==JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                Scanner filein = null;

                try {
                    filein = new Scanner(file);
                    if (file.isFile()){
                        while (filein.hasNextLine()){
                            String line = filein.nextLine()+"\n";
                            textArea.append(line);
                        }
                    }
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                finally {
                    filein.close();
                }
            }

        }
        if (e.getSource()==saveitem){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));

            int response = fileChooser.showSaveDialog(null);

            if (response==JFileChooser.APPROVE_OPTION){
                File file ;
                PrintWriter fileout = null;

                file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                try {
                    fileout = new PrintWriter(file);
                    fileout.println(textArea.getText());
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                finally {
                    fileout.close();
                }
            }
        }
        if (e.getSource()==exititem){
            System.exit(0);
        }
    }
}
