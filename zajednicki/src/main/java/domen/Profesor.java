package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Profesor implements ApstraktniDomenskiObjekat {

    private Long idProfesor;
    private String ime;
    private String prezime;
    private String brojTelefona;
    private String korisnickoIme;
    private String sifra;

    public Profesor() {
    }

    public Profesor(Long idProfesor, String ime, String prezime, String brojTelefona, String korisnickoIme, String sifra) {
        this.idProfesor = idProfesor;
        this.ime = ime;
        this.prezime = prezime;
        this.brojTelefona = brojTelefona;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
    }

    public Long getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Long idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(korisnickoIme, sifra);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Profesor other = (Profesor) obj;
        if (!Objects.equals(this.korisnickoIme, other.korisnickoIme)) {
            return false;
        }
        return Objects.equals(this.sifra, other.sifra);
    }

    @Override
    public String vratiNazivTabele() {
        return "profesor";
    }

    @Override
    public String alias() {
        return " p ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public String uslovZaSelect() {
        StringBuilder sb = new StringBuilder();
        if (korisnickoIme != null && !korisnickoIme.isEmpty()) {
            sb.append("p.korisnickoIme = '").append(korisnickoIme).append("'");
        }
        if (sifra != null && !sifra.isEmpty()) {
            if (sb.length() > 0) {
                sb.append(" AND ");
            }
            sb.append("p.sifra = '").append(sifra).append("'");
        }
        return sb.toString();
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime, prezime, brojTelefona, korisnickoIme, sifra";
    }

    @Override
    public String vratiVrednostZaUbacivanje() {
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(ime).append("', ")
                .append("'").append(prezime).append("', ")
                .append("'").append(brojTelefona).append("', ")
                .append("'").append(korisnickoIme).append("', ")
                .append("'").append(sifra).append("'");
        return sb.toString();
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("ime = '").append(ime).append("', ")
                .append("prezime = '").append(prezime).append("', ")
                .append("brojTelefona = '").append(brojTelefona).append("', ")
                .append("korisnickoIme = '").append(korisnickoIme).append("', ")
                .append("sifra = '").append(sifra).append("'");
        return sb.toString();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idProfesor = " + idProfesor;
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            Long id = rs.getLong("p.idProfesor");
            String imeP = rs.getString("p.ime");
            String prezimeP = rs.getString("p.prezime");
            String brTel = rs.getString("p.brojTelefona");
            String korIme = rs.getString("p.korisnickoIme");
            String sifraPr = rs.getString("p.sifra");

            lista.add(new Profesor(id, imeP, prezimeP, brTel, korIme, sifraPr));
        }
        return lista;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        if (rs.next()) {
            Long id = rs.getLong("p.idProfesor");
            String imeP = rs.getString("p.ime");
            String prezimeP = rs.getString("p.prezime");
            String brTel = rs.getString("p.brojTelefona");
            String korIme = rs.getString("p.korisnickoIme");
            String sifraPr = rs.getString("p.sifra");

            return new Profesor(id, imeP, prezimeP, brTel, korIme, sifraPr);
        }
        return null;
    }

    @Override
    public void postaviId(Long id) {
        this.idProfesor = id;
    }
}
