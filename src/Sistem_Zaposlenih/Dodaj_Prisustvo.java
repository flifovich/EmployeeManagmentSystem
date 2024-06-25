package Sistem_Zaposlenih;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class Dodaj_Prisustvo implements ActionListener, ItemListener {
    JFrame f;
    JLabel id, id1, id2, id3, id4, id5, id6, id7;
    JTextField t1, t2, t3;
    JButton b, b1;
    JComboBox<String> idDropdown, smenaDropdown, statusDropdown;
    ArrayList<String> idZaposlenih = new ArrayList<>();

    Dodaj_Prisustvo() {
        f = new JFrame("Dodaj Prisustvo");
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

        id1 = new JLabel("Dodaj Prisustvo:");
        id1.setBounds(280, 30, 500, 50);
        id1.setFont(new Font("Arial", Font.BOLD, 30));
        id1.setForeground(Color.black);
        id.add(id1);

        id2 = new JLabel("Izaberite ID:");
        id2.setBounds(50, 100, 150, 30);
        id2.setFont(new Font("Arial", Font.BOLD, 20));
        id.add(id2);

        idDropdown = new JComboBox<>();
        idDropdown.setBounds(200, 100, 150, 30);
        idDropdown.addItemListener(this);
        id.add(idDropdown);

        // Fetch id zaposlenih
        ucitajIdZaposlenih();

        id3 = new JLabel("Ime");
        id3.setBounds(50, 150, 150, 30);
        id3.setFont(new Font("Arial", Font.BOLD, 20));
        id.add(id3);

        t1 = new JTextField();
        t1.setBounds(200, 150, 150, 30);
        t1.setEditable(false);
        id.add(t1);

        id4 = new JLabel("Prezime");
        id4.setBounds(50, 200, 150, 30);
        id4.setFont(new Font("Arial", Font.BOLD, 20));
        id.add(id4);

        t2 = new JTextField();
        t2.setBounds(200, 200, 150, 30);
        t2.setEditable(false);
        id.add(t2);

        id5 = new JLabel("Email");
        id5.setBounds(50, 250, 150, 30);
        id5.setFont(new Font("Arial", Font.BOLD, 20));
        id.add(id5);

        t3 = new JTextField();
        t3.setBounds(200, 250, 150, 30);
        t3.setEditable(false);
        id.add(t3);

        id6 = new JLabel("Smena");
        id6.setBounds(50, 300, 150, 30);
        id6.setFont(new Font("Arial", Font.BOLD, 20));
        id.add(id6);

        smenaDropdown = new JComboBox<>(new String[]{"Prva smena", "Druga smena"});
        smenaDropdown.setBounds(200, 300, 150, 30);
        id.add(smenaDropdown);

        id7 = new JLabel("Status");
        id7.setBounds(50, 350, 150, 30);
        id7.setFont(new Font("Arial", Font.BOLD, 20));
        id.add(id7);

        statusDropdown = new JComboBox<>(new String[]{"Prisutan", "Odsutan"});
        statusDropdown.setBounds(200, 350, 150, 30);
        id.add(statusDropdown);

        b = new JButton("Dodaj");
        b.setBounds(200, 400, 150, 40);
        b.addActionListener(this);
        id.add(b);

        b1 = new JButton("Cancel");
        b1.setBounds(400, 400, 150, 40);
        b1.addActionListener(this);
        id.add(b1);

        f.setVisible(true);
        f.setSize(900, 600);
        f.setLocation(300, 100);
    }

    private void ucitajIdZaposlenih() {
        try {
            Konekcija obj = Konekcija.getInstance();
            String query = "SELECT id FROM zaposleni";
            Statement stmt = obj.getKonekcija().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String id = rs.getString("id");
                idZaposlenih.add(id);
                idDropdown.addItem(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ucitajDetaljeZaposlenih(String id) {
        try {
            Konekcija obj = Konekcija.getInstance();
            String query = "SELECT ime, prezime, email FROM zaposleni WHERE id = ?";
            PreparedStatement pst = obj.getKonekcija().prepareStatement(query);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                t1.setText(rs.getString("ime"));
                t2.setText(rs.getString("prezime"));
                t3.setText(rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b) {
            String id_zaposlenog = (String) idDropdown.getSelectedItem();
            String ime = t1.getText();
            String prezime = t2.getText();
            String email = t3.getText();
            String shift = (String) smenaDropdown.getSelectedItem();
            String status = (String) statusDropdown.getSelectedItem();

            try {
                Konekcija obj = Konekcija.getInstance();
                String query = "INSERT INTO prisustvo (id_zaposlenog, ime, prezime, email, smena, status) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement pst = obj.getKonekcija().prepareStatement(query);
                pst.setString(1, id_zaposlenog);
                pst.setString(2, ime);
                pst.setString(3, prezime);
                pst.setString(4, email);
                pst.setString(5, shift);
                pst.setString(6, status);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Prisustvo uspešno dodato.");
                obj.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Greška prilikom dodavanja prisustva.");
            }
        }
        if (e.getSource() == b1) {
            f.setVisible(false);
        }
    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            String selectedId = (String) idDropdown.getSelectedItem();
            ucitajDetaljeZaposlenih(selectedId);
        }
    }

    public static void main(String[] args) {
        new Dodaj_Prisustvo();
    }
}
