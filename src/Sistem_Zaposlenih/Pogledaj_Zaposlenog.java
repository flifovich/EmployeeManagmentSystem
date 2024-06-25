package Sistem_Zaposlenih;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Pogledaj_Zaposlenog implements ActionListener {
    JFrame f;
    JLabel l1;
    JTextField t1;
    JButton b1;

    Pogledaj_Zaposlenog() {
        f = new JFrame("Pogledaj Zaposlenog");
        f.setLayout(null);

        l1 = new JLabel("Unesite ID Zaposlenog:");
        l1.setBounds(50, 50, 200, 30);
        l1.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(l1);

        t1 = new JTextField();
        t1.setBounds(300, 50, 150, 30);
        f.add(t1);

        b1 = new JButton("Pogledaj");
        b1.setBounds(150, 100, 150, 40);
        b1.addActionListener(this);
        f.add(b1);

        f.setSize(550, 250);
        f.setLocation(500, 300);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            String id = t1.getText();
            new Prikazi_Zaposlenog(id);
            f.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Pogledaj_Zaposlenog();
    }
}
