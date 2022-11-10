import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ClientGUI implements ActionListener{
    private static JLabel label;
    private static JTextField userText;
    private static JLabel newLabel;
    private static JTextField newUserText;
    private static JButton submit;
    private static JLabel loginSuccess;
    public String user;
    public String newUser;

    public static void main (String [] args){
        JPanel panel = new JPanel();

        JFrame frame = new JFrame();
        frame.setSize(100,100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        label = new JLabel("User");
        label.setBounds(10, 20, 80, 25);
        panel.add(label);

        userText = new JTextField();
        userText.setBounds(100,20,165,25);
        panel.add(userText);

        newLabel = new JLabel("New User");
        newLabel.setBounds(10,50,80,25);
        panel.add(newLabel);
        
        // JPasswordField pass = new JPasswordField();
        newUserText = new JTextField();
        newUserText.setBounds(100,50,165,25);
        panel.add(newUserText);

        submit = new JButton("Login");
        submit.setBounds(10, 80, 80, 25);
        submit.addActionListener(new ClientGUI());
        panel.add(submit);

        loginSuccess = new JLabel("");
        loginSuccess.setBounds(10,110,300,25);
        panel.add(loginSuccess);
        loginSuccess.setText(null);


        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        user = userText.getText();
        newUser = newUserText.getText();
        
    }

}
