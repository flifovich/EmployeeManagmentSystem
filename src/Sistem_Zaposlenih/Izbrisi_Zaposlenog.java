package Sistem_Zaposlenih;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class Izbrisi_Zaposlenog implements ActionListener, ItemListener {
    JFrame f;
    JLabel id, id1, id2, id3, id4;
    JComboBox<String> idDropdown;
    JTextField poljeIme, poljePrezime;
    JButton b, b1;
    ArrayList<String> idZaposlenih = new ArrayList<>();

    Izbrisi_Zaposlenog() {
        f = new JFrame("Izbrisi Zaposlenog");
        f.setBackground(Color.WHITE);
        f.setLayout(null);

        id = new JLabel();
        id.setBounds(0, 0, 900, 600);
        id.setLayout(null);
        f.add(id);

        id1 = new JLabel("Izbrisi Zaposlenog:");
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

        id3 = new JLabel("Ime:");
        id3.setBounds(50, 150, 150, 30);
        id3.setFont(new Font("Arial", Font.BOLD, 20));
        id.add(id3);

        poljeIme = new JTextField();
        poljeIme.setBounds(200, 150, 150, 30);
        poljeIme.setEditable(false);
        id.add(poljeIme);

        id4 = new JLabel("Prezime:");
        id4.setBounds(50, 200, 150, 30);
        id4.setFont(new Font("Arial", Font.BOLD, 20));
        id.add(id4);

        poljePrezime = new JTextField();
        poljePrezime.setBounds(200, 200, 150, 30);
        poljePrezime.setEditable(false);
        id.add(poljePrezime);

        // Fetch employee IDs
        ucitajIdZaposlenih();

        b = new JButton("Izbrisi");
        b.setBounds(200, 250, 150, 40);
        b.addActionListener(this);
        id.add(b);

        b1 = new JButton("Cancel");
        b1.setBounds(400, 250, 150, 40);
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
            obj.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ucitajDetaljeZaposlenih(String id) {
        try {
            Konekcija obj = Konekcija.getInstance();
            String query = "SELECT ime, prezime FROM zaposleni WHERE id = ?";
            PreparedStatement pst = obj.getKonekcija().prepareStatement(query);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                poljeIme.setText(rs.getString("ime"));
                poljePrezime.setText(rs.getString("prezime"));
            }

            obj.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            String selectedId = (String) idDropdown.getSelectedItem();
            ucitajDetaljeZaposlenih(selectedId);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b) {
            String selectedId = (String) idDropdown.getSelectedItem();
            String ime = poljeIme.getText();
            String prezime = poljePrezime.getText();
            if (selectedId != null) {
                int confirmation = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da želite izbrisati zaposlenog sa ID: " + selectedId + ", Ime: " + ime + ", Prezime: " + prezime + "?", "Potvrda brisanja", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    try {
                        Konekcija obj = Konekcija.getInstance();
                        String query = "DELETE FROM zaposleni WHERE id = ?";
                        PreparedStatement pst = obj.getKonekcija().prepareStatement(query);
                        pst.setString(1, selectedId);
                        int rowsAffected = pst.executeUpdate();
                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(null, "Zaposleni uspešno izbrisan.");
                            idDropdown.removeItem(selectedId);
                            poljeIme.setText("");
                            poljePrezime.setText("");
                        } else {
                            JOptionPane.showMessageDialog(null, "Greška prilikom brisanja zaposlenog.");
                        }
                        obj.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Greška prilikom brisanja zaposlenog.");
                    }
                }
            }
        } else if (e.getSource() == b1) {
            f.setVisible(false);
            f.dispose();
        }
    }

    public static void main(String[] args) {
        new Izbrisi_Zaposlenog();
    }
}
