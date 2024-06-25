package Sistem_Zaposlenih;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.sql.*;

public class Pogledaj_Odsustvo implements ActionListener {
    JFrame f;
    JTable table;
    JTextField searchField;
    JButton searchButton;
    DefaultTableModel model;

    Pogledaj_Odsustvo() {
        f = new JFrame("Pogledaj Odsustva");
        f.setLayout(new BorderLayout());

        
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"ID", "Ime", "Prezime", "Email", "Datum Početka", "Datum Kraja", "Razlog", "Vreme"});

        
        table = new JTable(model);

        
        fetchLeaveRecords("");

        
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchField = new JTextField(20);
        searchButton = new JButton("Pretraži");
        searchButton.addActionListener(this);
        searchPanel.add(new JLabel("Pretraži po imenu ili ID-u:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        
        f.add(searchPanel, BorderLayout.NORTH);
        f.add(new JScrollPane(table), BorderLayout.CENTER);

        f.setSize(800, 400);
        f.setLocation(300, 200);
        f.setVisible(true);
    }

    private void fetchLeaveRecords(String searchQuery) {
        try {
            Konekcija obj = Konekcija.getInstance();
            String query = "SELECT * FROM odsustvo";
            if (!searchQuery.isEmpty()) {
                query += " WHERE ime LIKE ? OR id_zaposlenog LIKE ?";
            }
            PreparedStatement pst = obj.getKonekcija().prepareStatement(query);
            if (!searchQuery.isEmpty()) {
                pst.setString(1, "%" + searchQuery + "%");
                pst.setString(2, "%" + searchQuery + "%");
            }
            ResultSet rs = pst.executeQuery();
            model.setRowCount(0);
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("id_zaposlenog"),
                    rs.getString("ime"),
                    rs.getString("prezime"),
                    rs.getString("email"),
                    rs.getDate("datum_pocetka"),
                    rs.getDate("datum_kraja"),
                    rs.getString("razlog"),
                    rs.getTimestamp("timestamp")
                });
            }
            obj.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            String searchQuery = searchField.getText();
            fetchLeaveRecords(searchQuery);
        }
    }

    public static void main(String[] args) {
        new Pogledaj_Odsustvo();
    }
}
