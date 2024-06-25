package Sistem_Zaposlenih;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Promeni_Detalje implements ActionListener, ItemListener {
    JFrame f;
    JLabel id, id1, id2, id3, id4, id5, id6, id7, id8, id9;
    JTextField t1, t2, t3, t4, t5, t6, t7, t8;
    JButton b, b1;
    JComboBox<String> idDropdown;
    ArrayList<String> employeeIds = new ArrayList<>();

    Promeni_Detalje() {
        f = new JFrame("Promeni Detalje Zaposlenog");
        f.setBackground(Color.WHITE);
        f.setLayout(null);

        id = new JLabel();
        id.setBounds(0, 0, 900, 700);
        id.setLayout(null);
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("Sistem_Zaposlenih/Icon/PocetnaStrana.jpg"));
        Image il = img.getImage().getScaledInstance(900, 700, Image.SCALE_DEFAULT);
        ImageIcon img1 = new ImageIcon(il);
        id.setIcon(img1);
        f.add(id);

        id1 = new JLabel("Promeni Detalje Zaposlenog:");
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

        id3 = new JLabel("Ime");
        id3.setBounds(50, 150, 150, 30);
        id3.setFont(new Font("Arial", Font.BOLD, 20));
        id.add(id3);

        t1 = new JTextField();
        t1.setBounds(200, 150, 150, 30);
        id.add(t1);

        id4 = new JLabel("Prezime");
        id4.setBounds(50, 200, 150, 30);
        id4.setFont(new Font("Arial", Font.BOLD, 20));
        id.add(id4);

        t2 = new JTextField();
        t2.setBounds(200, 200, 150, 30);
        id.add(t2);

        id5 = new JLabel("Adresa");
        id5.setBounds(50, 250, 150, 30);
        id5.setFont(new Font("Arial", Font.BOLD, 20));
        id.add(id5);

        t3 = new JTextField();
        t3.setBounds(200, 250, 150, 30);
        id.add(t3);

        id6 = new JLabel("Email");
        id6.setBounds(50, 300, 150, 30);
        id6.setFont(new Font("Arial", Font.BOLD, 20));
        id.add(id6);

        t4 = new JTextField();
        t4.setBounds(200, 300, 150, 30);
        id.add(t4);

        id7 = new JLabel("Datum Rodjenja (dd.MM.yyyy)");
        id7.setBounds(50, 350, 250, 30);
        id7.setFont(new Font("Arial", Font.BOLD, 20));
        id.add(id7);

        t8 = new JTextField();
        t8.setBounds(300, 350, 150, 30);
        id.add(t8);

        id8 = new JLabel("Broj Telefona");
        id8.setBounds(50, 400, 150, 30);
        id8.setFont(new Font("Arial", Font.BOLD, 20));
        id.add(id8);

        t5 = new JTextField();
        t5.setBounds(200, 400, 150, 30);
        id.add(t5);

        id9 = new JLabel("Pozicija");
        id9.setBounds(50, 450, 150, 30);
        id9.setFont(new Font("Arial", Font.BOLD, 20));
        id.add(id9);

        t6 = new JTextField();
        t6.setBounds(200, 450, 150, 30);
        id.add(t6);

        JLabel id10 = new JLabel("Plata");
        id10.setBounds(50, 500, 150, 30);
        id10.setFont(new Font("Arial", Font.BOLD, 20));
        id.add(id10);

        t7 = new JTextField();
        t7.setBounds(200, 500, 150, 30);
        id.add(t7);

        b = new JButton("Promeni");
        b.setBounds(200, 550, 150, 40);
        b.addActionListener(this);
        id.add(b);

        b1 = new JButton("Cancel");
        b1.setBounds(400, 550, 150, 40);
        b1.addActionListener(this);
        id.add(b1);

        f.setVisible(true);
        f.setSize(900, 700);
        f.setLocation(300, 100);

        // Fetch employee IDs after initializing all components
        fetchEmployeeIds();
    }

    private void fetchEmployeeIds() {
        try {
            Konekcija obj = Konekcija.getInstance();
            String query = "SELECT id FROM zaposleni";
            Statement stmt = obj.getKonekcija().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String id = rs.getString("id");
                employeeIds.add(id);
                idDropdown.addItem(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fetchEmployeeDetails(String id) {
        try {
            Konekcija obj = Konekcija.getInstance();
            String query = "SELECT * FROM zaposleni WHERE id = ?";
            PreparedStatement pst = obj.getKonekcija().prepareStatement(query);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                t1.setText(rs.getString("ime"));
                t2.setText(rs.getString("prezime"));
                t3.setText(rs.getString("adresa"));
                t4.setText(rs.getString("email"));
                t8.setText(rs.getString("datum_rodjenja"));
                t5.setText(rs.getString("broj_telefona"));
                t6.setText(rs.getString("pozicija"));
                t7.setText(rs.getString("plata"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b) {
            String id = (String) idDropdown.getSelectedItem();
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
                JOptionPane.showMessageDialog(null, "Greška: Unesite datum u formatu dd.MM.yyyy.");
                return;
            }

            try {
                Konekcija obj = Konekcija.getInstance();
                String query = "UPDATE zaposleni SET ime = ?, prezime = ?, adresa = ?, email = ?, datum_rodjenja = ?, broj_telefona = ?, pozicija = ?, plata = ? WHERE id = ?";
                PreparedStatement pst = obj.getKonekcija().prepareStatement(query);
                pst.setString(1, ime);
                pst.setString(2, prezime);
                pst.setString(3, adresa);
                pst.setString(4, email);
                pst.setDate(5, sqlDate);
                pst.setString(6, brojTelefona);
                pst.setString(7, pozicija);
                pst.setString(8, plata);
                pst.setString(9, id);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Podaci uspešno izmenjeni.");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Greška prilikom izmene podataka.");
            }
        } else if (e.getSource() == b1) {
            f.setVisible(false);
            f.dispose();
        }
    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            String selectedId = (String) idDropdown.getSelectedItem();
            fetchEmployeeDetails(selectedId);
        }
    }

    public static void main(String[] args) {
        new Promeni_Detalje();
    }
}
