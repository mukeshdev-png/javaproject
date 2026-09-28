package bankmanagementsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JButton signup,signin,clear;
    JTextField cardno;
    JPasswordField passwordfiled;

    Login(){
        setTitle("ATM Login Page");

        setExtendedState(JFrame.MAXIMIZED_BOTH); // ✅ full screen
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Title
        JLabel title = new JLabel("Welcome to the Login Page");
        title.setBounds(250, 40, 300, 30);

        // Card Label
        JLabel card = new JLabel("Card No:");
        card.setBounds(200, 120, 100, 25);

        // PIN Label
        JLabel pin = new JLabel("PIN:");
        pin.setBounds(200, 170, 100, 25);

        // Fields
        cardno = new JTextField();
        cardno.setBounds(300, 120, 200, 25);

        passwordfiled = new JPasswordField();
        passwordfiled.setBounds(300, 170, 200, 25);

        // Buttons
        signin = new JButton("Sign In");
        signin.setBounds(200, 250, 100, 30);
        signin.addActionListener(this);

        clear = new JButton("Clear");
        clear.setBounds(320, 250, 100, 30);
        clear.addActionListener(this);

        signup = new JButton("Sign Up");
        signup.setBounds(440, 250, 100, 30);
        signup.addActionListener(this);

        // ✅ ADD to THIS frame (not frame)
        add(title);
        add(card);
        add(pin);
        add(cardno);
        add(passwordfiled);
        add(signin);
        add(clear);
        add(signup);

        setVisible(true); // ✅ correct
    }
    public void actionPerformed(ActionEvent ac){
        if(ac.getSource()==clear){
            cardno.setText("");
            passwordfiled.setText("");
        } else if (ac.getSource()==signin) {
            System.out.println("ballu");
            String CARDNO = cardno.getText();
            String PINNO = passwordfiled.getText(); 
            try{
                Connection con = DBcon.getConnection();
                String query = "select * from Signupthreelogs where card_number = '"+CARDNO+"' and pin = '"+PINNO+"'";                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    setVisible(false);
                    new TransactionUI(PINNO,CARDNO).setVisible(true);

                }
                else{
                    JOptionPane.showMessageDialog(null,"Cardnumber or Password is incorrect");
                }

            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.println("signup");

        } else if (ac.getSource()==signup) {
            setVisible(false);
            new Signup().setVisible(true);

        }


    }
    public static void main() {

       new Login();


    }
}
