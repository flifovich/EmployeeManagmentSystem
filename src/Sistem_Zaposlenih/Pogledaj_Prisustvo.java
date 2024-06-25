package Sistem_Zaposlenih;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.sql.*;

public class Pogledaj_Prisustvo implements ActionListener {
    JFrame okvir;
    JTable tabela;
    JTextField pretragaPolje;
    JButton pretragaDugme;
    DefaultTableModel model;

    Pogledaj_Prisustvo() {
        okvir = new JFrame("Pogledaj Prisustvo");
        okvir.setLayout(new BorderLayout());

        // Kreiranje modela tabele i postavljanje naziva kolona
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"ID", "Ime", "Prezime", "Email", "Smena", "Status", "Vreme"});

        // Kreiranje tabele i postavljanje modela
        tabela = new JTable(model);

        // ucitaj sva prisustva
        ucitajPrisustva("");

        // Kreiranje polja za pretragu i dugmeta
        JPanel pretragaPanel = new JPanel();
        pretragaPanel.setLayout(new FlowLayout());
        pretragaPolje = new JTextField(20);
        pretragaDugme = new JButton("Pretraži");
        pretragaDugme.addActionListener(this);
        pretragaPanel.add(new JLabel("Pretraži po imenu ili ID-u:"));
        pretragaPanel.add(pretragaPolje);
        pretragaPanel.add(pretragaDugme);

        // Dodavanje pretraga panela i tabele u okvir
        okvir.add(pretragaPanel, BorderLayout.NORTH);
        okvir.add(new JScrollPane(tabela), BorderLayout.CENTER);

        okvir.setSize(800, 400);
        okvir.setLocation(300, 200);
        okvir.setVisible(true);
    }

    private void ucitajPrisustva(String upitZaPretragu) {
        try {
            Konekcija konekcija = Konekcija.getInstance();
            String upit = "SELECT * FROM prisustvo";
            if (!upitZaPretragu.isEmpty()) {
                upit += " WHERE ime LIKE ? OR id_zaposlenog LIKE ?";
            }
            PreparedStatement pst = konekcija.getKonekcija().prepareStatement(upit);
            if (!upitZaPretragu.isEmpty()) {
                pst.setString(1, "%" + upitZaPretragu + "%");
                pst.setString(2, "%" + upitZaPretragu + "%");
            }
            ResultSet rs = pst.executeQuery();
            model.setRowCount(0); // Resetovanje tabele
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id_zaposlenog"),
                    rs.getString("ime"),
                    rs.getString("prezime"),
                    rs.getString("email"),
                    rs.getString("smena"),
                    rs.getString("status"),
                    rs.getTimestamp("timestamp")
                });
            }
            konekcija.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pretragaDugme) {
            String upitZaPretragu = pretragaPolje.getText();
            ucitajPrisustva(upitZaPretragu);
        }
    }

    public static void main(String[] args) {
        new Pogledaj_Prisustvo();
    }
}
