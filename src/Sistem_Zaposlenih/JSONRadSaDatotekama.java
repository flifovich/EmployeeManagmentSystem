package Sistem_Zaposlenih;

import Sistem_Zaposlenih.models.Zaposleni;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;

public class JSONRadSaDatotekama {
    public static void sacuvajZaposlenog(Zaposleni zaposleni) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("id", zaposleni.getId());
        jsonObj.put("ime", zaposleni.getIme());
        jsonObj.put("prezime", zaposleni.getPrezime());
        jsonObj.put("adresa", zaposleni.getAdresa());
        jsonObj.put("email", zaposleni.getEmail());
        jsonObj.put("datum_rodjenja", zaposleni.getDatumRodjenja().toString());
        jsonObj.put("broj_telefona", zaposleni.getBrojTelefona());
        jsonObj.put("pozicija", zaposleni.getPozicija());
        jsonObj.put("plata", zaposleni.getPlata());

        try (FileWriter file = new FileWriter("zaposleni.json")) {
            file.write(jsonObj.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new Login();

        // Primer rada sa JSON datotekom
        Zaposleni zaposleni = Zaposleni.ucitajPoId(1);
        if (zaposleni != null) {
            zaposleni.prikaziDetalje();
            JSONRadSaDatotekama.sacuvajZaposlenog(zaposleni);
        } else {
            System.out.println("Zaposleni sa datim ID nije pronađen.");
        }
    }
}
