package Sistem_Zaposlenih.models;

import Sistem_Zaposlenih.Konekcija;
import java.sql.*;

public class Prisustvo implements Model {
    private int id;
    private int idZaposlenog;
    private String ime;
    private String prezime;
    private String email;
    private String smena;
    private String status;
    private Timestamp vreme;

    public Prisustvo(int id, int idZaposlenog, String ime, String prezime, String email, String smena, String status, Timestamp vreme) {
        this.id = id;
        this.idZaposlenog = idZaposlenog;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.smena = smena;
        this.status = status;
        this.vreme = vreme;
    }

    public int getId() {
        return id;
    }

    public int getIdZaposlenog() {
        return idZaposlenog;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getEmail() {
        return email;
    }

    public String getSmena() {
        return smena;
    }

    public String getStatus() {
        return status;
    }

    public Timestamp getVreme() {
        return vreme;
    }

    public static Prisustvo ucitajPoId(int id) {
        try {
            Konekcija konekcija = Konekcija.getInstance();
            String upit = "SELECT * FROM prisustvo WHERE id = ?";
            PreparedStatement pst = konekcija.getKonekcija().prepareStatement(upit);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new Prisustvo(
                    rs.getInt("id"),
                    rs.getInt("id_zaposlenog"),
                    rs.getString("ime"),
                    rs.getString("prezime"),
                    rs.getString("email"),
                    rs.getString("smena"),
                    rs.getString("status"),
                    rs.getTimestamp("vreme")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void prikaziDetalje() {
        System.out.println("ID: " + id + ", ID Zaposlenog: " + idZaposlenog + ", Ime: " + ime + ", Prezime: " + prezime + ", Email: " + email + ", Smena: " + smena + ", Status: " + status + ", Vreme: " + vreme);
    }

    
}
