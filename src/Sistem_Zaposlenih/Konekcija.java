package Sistem_Zaposlenih;

import java.sql.*;

public class Konekcija {
    private static Konekcija instance;
    private Connection konekcija;
    private Statement izjava;

    private Konekcija() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            konekcija = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistemoop", "root", "");
            izjava = konekcija.createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static synchronized Konekcija getInstance() {
        if (instance == null) {
            instance = new Konekcija();
        }
        return instance;
    }

    public Connection getKonekcija() {
        return konekcija;
    }

    public Statement getIzjava() {
        return izjava;
    }

    public void close() {
        try {
            if (izjava != null) izjava.close();
            if (konekcija != null) konekcija.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
