package Sistem_Zaposlenih;

import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class Prikazi_Zaposlenog {
    JFrame f;
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9;
    String ime, prezime, adresa, email, datumRodjenja, brojTelefona, pozicija, plata;
    
    Prikazi_Zaposlenog(String id) {
        try {
            Konekcija obj = Konekcija.getInstance();
            String query = "SELECT * FROM zaposleni WHERE id = ?";
            PreparedStatement pst = obj.getKonekcija().prepareStatement(query);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                ime = rs.getString("ime");
                prezime = rs.getString("prezime");
                adresa = rs.getString("adresa");
                email = rs.getString("email");
                datumRodjenja = rs.getString("datum_rodjenja");
                brojTelefona = rs.getString("broj_telefona");
                pozicija = rs.getString("pozicija");
                plata = rs.getString("plata");
            } else {
                JOptionPane.showMessageDialog(null, "Nema zaposlenog sa datim ID-jem.");
                return;
            }

            obj.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        f = new JFrame("Detalji Zaposlenog");
        f.setLayout(null);

        l1 = new JLabel("ID: " + id);
        l1.setBounds(50, 50, 300, 30);
        l1.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(l1);

        l2 = new JLabel("Ime: " + ime);
        l2.setBounds(50, 100, 300, 30);
        l2.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(l2);

        l3 = new JLabel("Prezime: " + prezime);
        l3.setBounds(50, 150, 300, 30);
        l3.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(l3);

        l4 = new JLabel("Adresa: " + adresa);
        l4.setBounds(50, 200, 300, 30);
        l4.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(l4);

        l5 = new JLabel("Email: " + email);
        l5.setBounds(50, 250, 300, 30);
        l5.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(l5);

        l6 = new JLabel("Datum Rodjenja: " + datumRodjenja);
        l6.setBounds(50, 300, 300, 30);
        l6.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(l6);

        l7 = new JLabel("Broj Telefona: " + brojTelefona);
        l7.setBounds(50, 350, 300, 30);
        l7.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(l7);

        l8 = new JLabel("Pozicija: " + pozicija);
        l8.setBounds(50, 400, 300, 30);
        l8.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(l8);

        l9 = new JLabel("Plata: " + plata);
        l9.setBounds(50, 450, 300, 30);
        l9.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(l9);

        f.setSize(400, 550);
        f.setLocation(500, 200);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        new Pogledaj_Zaposlenog();
    }
}
