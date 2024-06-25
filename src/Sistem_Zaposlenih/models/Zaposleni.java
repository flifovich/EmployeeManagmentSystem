package Sistem_Zaposlenih.models;

import Sistem_Zaposlenih.Konekcija;

import java.sql.*;

public class Zaposleni implements Model {
    private int id;
    private String ime;
    private String prezime;
    private String adresa;
    private String email;
    private Date datumRodjenja;
    private String brojTelefona;
    private String pozicija;
    private double plata;

    public Zaposleni(int id, String ime, String prezime, String adresa, String email, Date datumRodjenja, String brojTelefona, String pozicija, double plata) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.email = email;
        this.datumRodjenja = datumRodjenja;
        this.brojTelefona = brojTelefona;
        this.pozicija = pozicija;
        this.plata = plata;
        
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public void setPozicija(String pozicija) {
        this.pozicija = pozicija;
    }

    public void setPlata(double plata) {
        this.plata = plata;
    }

    public int getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getEmail() {
        return email;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public String getPozicija() {
        return pozicija;
    }

    public double getPlata() {
        return plata;
    }

    public static Zaposleni ucitajPoId(int id) {
        try {
            Konekcija konekcija = Konekcija.getInstance();
            String upit = "SELECT * FROM zaposleni WHERE id = ?";
            PreparedStatement pst = konekcija.getKonekcija().prepareStatement(upit);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new Zaposleni(
                        
                    rs.getInt("id"),
                    rs.getString("ime"),
                    rs.getString("prezime"),
                    rs.getString("adresa"),
                    rs.getString("email"),
                    rs.getDate("datum_rodjenja"),
                    rs.getString("broj_telefona"),
                    rs.getString("pozicija"),
                    rs.getDouble("plata")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void prikaziDetalje() {
        System.out.println("ID: " + id + ", Ime: " + ime + ", Prezime: " + prezime + ", Adresa: " + adresa + ", Email: " + email + ", Datum Rodjenja: " + datumRodjenja + ", Broj Telefona: " + brojTelefona + ", Pozicija: " + pozicija + ", Plata: " + plata);
    }
}
