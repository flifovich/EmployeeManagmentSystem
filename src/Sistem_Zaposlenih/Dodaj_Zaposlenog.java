package Sistem_Zaposlenih;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Dodaj_Zaposlenog implements ActionListener {
    JLabel id, id1, id2, id3, id4, id5, id6, id7, id8, id9;
    JFrame f;
    JTextField t1, t2, t3, t4, t5, t6, t7, t8;
    JButton b, b1;

    Dodaj_Zaposlenog() {
        f = new JFrame("Dodaj Zaposlenog");
        f.setBackground(Color.WHITE);
        f.setLayout(null);

        id = new JLabel();
        id.setBounds(0, 0, 900, 600);
        id.setLayout(null);
//        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("Sistem_Zaposlenih/Icon/PocetnaStrana.jpg"));
//        Image il = img.getImage().getScaledInstance(900, 600, Image.SCALE_DEFAULT);
//        ImageIcon img1 = new ImageIcon(il);
//        id.setIcon(img1);
        f.add(id);

        id1 = new JLabel("Dodaj Novog Zaposlenog:");
        id1.setBounds(280, 30, 500, 50);
        id1.setFont(new Font("Arial", Font.BOLD, 30));
        id1.setForeground(Color.black);
        id.add(id1);

        id2 = new JLabel("Ime");
        id2.setBounds(50, 100, 150, 30);
        id2.setFont(new Font("Arial", Font.BOLD, 20));
        id.add(id2);

        t1 = new JTextField();
        t1.setBounds(200, 100, 150, 30);
        id.add(t1);

        id3 = new JLabel("Prezime");
        id3.setBounds(50, 150, 150, 30);
        id3.setFont(new Font("Arial", Font.BOLD, 20));
        id.add(id3);

        t2 = new JTextField();
        t2.setBounds(200, 150, 150, 30);
        id.add(t2);

        id4 = new JLabel("Adresa");
        id4.setBounds(50, 200, 150, 30);
        id4.setFont(new Font("Arial", Font.BOLD, 20));
        id.add(id4);

        t3 = new JTextField();
        t3.setBounds(200, 200, 150, 30);
        id.add(t3);

        id5 = new JLabel("Email");
        id5.setBounds(50, 250, 150, 30);
        id5.setFont(new Font("Arial", Font.BOLD, 20));
        id.add(id5);

        t4 = new JTextField();
        t4.setBounds(200, 250, 150, 30);
        id.add(t4);

        id9 = new JLabel("Datum Rodjenja (dd.mm.yyyy)");
        id9.setBounds(50, 300, 250, 30);
        id9.setFont(new Font("Arial", Font.BOLD, 20));
        id.add(id9);

        t8 = new JTextField();
        t8.setBounds(300, 300, 150, 30);
        id.add(t8);

        id6 = new JLabel("Broj Telefona");
        id6.setBounds(50, 350, 150, 30);
        id6.setFont(new Font("Arial", Font.BOLD, 20));
        id.add(id6);

        t5 = new JTextField();
        t5.setBounds(200, 350, 150, 30);
        id.add(t5);

        id7 = new JLabel("Pozicija");
        id7.setBounds(50, 400, 150, 30);
        id7.setFont(new Font("Arial", Font.BOLD, 20));
        id.add(id7);

        t6 = new JTextField();
        t6.setBounds(200, 400, 150, 30);
        id.add(t6);

        id8 = new JLabel("Plata");
        id8.setBounds(50, 450, 150, 30);
        id8.setFont(new Font("Arial", Font.BOLD, 20));
        id.add(id8);

        t7 = new JTextField();
        t7.setBounds(200, 450, 150, 30);
        id.add(t7);

        b = new JButton("Dodaj");
        b.setBounds(200, 500, 150, 40);
        b.addActionListener(this);
        id.add(b);

        b1 = new JButton("Odustani");
        b1.setBounds(400, 500, 150, 40);
        b1.addActionListener(this);
        id.add(b1);

        f.setVisible(true);
        f.setSize(900, 600);
        f.setLocation(300, 100);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b) {
            String ime = t1.getText();
            String prezime = t2.getText();
            String adresa = t3.getText();
            String email = t4.getText();
            String datumRodjenjaStr = t8.getText();
            String brojTelefona = t5.getText();
            String pozicija = t6.getText();
            String plata = t7.getText();

            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            java.util.Date parsedDate;
            java.sql.Date sqlDate = null;
            try {
                parsedDate = format.parse(datumRodjenjaStr);
                sqlDate = new java.sql.Date(parsedDate.getTime());
            } catch (ParseException pe) {
                JOptionPane.showMessageDialog(null, "Greška: Unesite datum u formatu dd.mm.yyyy.");
                return;
            }

            try {
                Konekcija konekcija = Konekcija.getInstance();
                String upit = "INSERT INTO zaposleni (ime, prezime, adresa, email, datum_rodjenja, broj_telefona, pozicija, plata) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pst = konekcija.getKonekcija().prepareStatement(upit);
                pst.setString(1, ime);
                pst.setString(2, prezime);
                pst.setString(3, adresa);
                pst.setString(4, email);
                pst.setDate(5, sqlDate);
                pst.setString(6, brojTelefona);
                pst.setString(7, pozicija);
                pst.setString(8, plata);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Zaposleni uspešno dodat.");
                f.setVisible(false);
                new PocetnaStrana().setVisible(true);
                konekcija.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Greška prilikom dodavanja zaposlenog.");
            }
        }
        if (e.getSource() == b1) {
            f.setVisible(false);
//            new PocetnaStrana().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Dodaj_Zaposlenog();
    }
}