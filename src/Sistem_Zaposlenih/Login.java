package Sistem_Zaposlenih;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    JFrame f;
    JLabel l1, l2;
    JTextField t1;
    JPasswordField t2;
    JButton b1, b2;

    Login() {
        f = new JFrame("Login");
        f.setBackground(Color.WHITE);
        f.setLayout(null);

        l1 = new JLabel("Korisnicko ime");
        l1.setBounds(40, 20, 100, 30);
        f.add(l1);

        l2 = new JLabel("Lozinka");
        l2.setBounds(40, 70, 100, 30);
        f.add(l2);

        t1 = new JTextField();
        t1.setBounds(150, 20, 150, 30);
        f.add(t1);

        t2 = new JPasswordField();
        t2.setBounds(150, 70, 150, 30);
        f.add(t2);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Sistem_Zaposlenih/Icon/loginIcon.jpg"));
        Image i2 = i1.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(350, 20, 150, 150);
        f.add(l3);

        b1 = new JButton("Login");
        b1.setBackground(Color.BLACK);
        b1.setBounds(40, 140, 120, 30);
        b1.addActionListener(this);
        b1.setForeground(Color.WHITE);
        f.add(b1);

        b2 = new JButton("Close");
        b2.setBackground(Color.BLACK);
        b2.setBounds(180, 140, 120, 30);
        b2.addActionListener(this);
        b2.setForeground(Color.WHITE);
        f.add(b2);

        f.setVisible(true);
        f.setSize(500, 240);
        f.setLocation(400, 300);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ee) {
        if (ee.getSource() == b1) {
            try {
                Konekcija kon = Konekcija.getInstance();
                String name = t1.getText();
                String pass = new String(t2.getPassword());
                String q = "SELECT * FROM logindata WHERE username=? AND password=?";
                PreparedStatement pst = kon.getKonekcija().prepareStatement(q);
                pst.setString(1, name);
                pst.setString(2, pass);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Uspesno prijavljeni!");
                    f.setVisible(false);
                    f.dispose(); // Close the login frame
                    new PocetnaStrana().setVisible(true); // Open the main window
                } else {
                    JOptionPane.showMessageDialog(null, "Pogresan username/lozinka");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (ee.getSource() == b2) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
