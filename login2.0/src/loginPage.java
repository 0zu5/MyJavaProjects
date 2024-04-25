import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class loginPage implements ActionListener {
    JFrame frame = new JFrame();
    JButton loginButton = new JButton("Log in");
    JButton resetButton = new JButton("Reset");
    JTextField userIDfield = new JTextField();
    JPasswordField userPassword = new JPasswordField();
    JLabel userIdlabel = new JLabel("userID:");
    JLabel userpasslabel = new JLabel("Password:");
    JLabel massageLabel = new JLabel("");
    HashMap<String,String> loginInfo = new HashMap<String,String>();
    loginPage(HashMap<String,String> loginInfoOriginal){
        loginInfo = loginInfoOriginal;

        userIdlabel.setBounds(50,100,75,25);
        userpasslabel.setBounds(50,150,75,25);

        massageLabel.setBounds(150,250,250,35);
        massageLabel.setFont(new Font(null,Font.ITALIC,25));

        userIDfield.setBounds(125,100,200,25);
        userPassword.setBounds(125,150,200,25);

        loginButton.setBounds(125,200,100,25);
        loginButton.addActionListener(this);

        resetButton.setBounds(225,200,100,25);
        resetButton.addActionListener(this);



        frame.add(loginButton);
        frame.add(resetButton);
        frame.add(userIDfield);
        frame.add(userPassword);
        frame.add(massageLabel);
        frame.add(userIdlabel);
        frame.add(userpasslabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==resetButton){
            userIDfield.setText("");
            userPassword.setText("");
            massageLabel.setText("");
        }
        if (e.getSource()==loginButton){
            String userID = userIDfield.getText();
            String userPass = String.valueOf(userPassword.getPassword());

            if (loginInfo.containsKey(userID)){
                if (loginInfo.get(userID).equals(userPass)){
                    massageLabel.setForeground(Color.green);
                    massageLabel.setText("login succeeded");
                    frame.dispose();
                    WelcomePage welcomepage = new WelcomePage(userID);
                }
                else {
                    massageLabel.setForeground(Color.red);
                    massageLabel.setText("Wrong");

                }
            }
            else
            {
                massageLabel.setForeground(Color.red);
                massageLabel.setText("Wrong");}

        }

    }
}
