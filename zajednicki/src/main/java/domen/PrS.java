package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Una
 */
public class PrS implements ApstraktniDomenskiObjekat {

    private Date datumIzdavanja;
    private Profesor profesor;
    private Sertifikat sertifikat;

    public PrS() {
    }

    public PrS(Date datumIzdavanja, Profesor profesor, Sertifikat sertifikat) {
        this.datumIzdavanja = datumIzdavanja;
        this.profesor = profesor;
        this.sertifikat = sertifikat;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Sertifikat getSertifikat() {
        return sertifikat;
    }

    public void setSertifikat(Sertifikat sertifikat) {
        this.sertifikat = sertifikat;
    }

    @Override
    public String toString() {
        return profesor + " " + sertifikat + " " + datumIzdavanja;
    }

    @Override
    public int hashCode() {
        return Objects.hash(datumIzdavanja, profesor, sertifikat);
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
        final PrS other = (PrS) obj;
        if (!Objects.equals(this.datumIzdavanja, other.datumIzdavanja)) {
            return false;
        }
        if (!Objects.equals(this.profesor, other.profesor)) {
            return false;
        }
        return Objects.equals(this.sertifikat, other.sertifikat);
    }

    @Override
    public String vratiNazivTabele() {
        return "pr_s";
    }

    @Override
    public String alias() {
        return " ps ";
    }

    @Override
    public String join() {
        return " JOIN profesor p ON ps.profesor = p.idProfesor "
                + " JOIN sertifikat s ON ps.sertifikat = s.idSertifikat ";
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "profesor, sertifikat, datumIzdavanja";
    }

    @Override
    public String vratiVrednostZaUbacivanje() {
        StringBuilder sb = new StringBuilder();
        sb.append(profesor.getIdProfesor()).append(", ")
                .append(sertifikat.getIdSertifikat()).append(", ")
                .append("'").append(new java.sql.Date(datumIzdavanja.getTime())).append("'");
        return sb.toString();
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("profesor = ").append(profesor.getIdProfesor()).append(", ")
                .append("sertifikat = ").append(sertifikat.getIdSertifikat()).append(", ")
                .append("datumIzdavanja = '").append(new java.sql.Date(datumIzdavanja.getTime())).append("'");
        return sb.toString();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "profesor = " + profesor.getIdProfesor()
                + " AND sertifikat = " + sertifikat.getIdSertifikat()
                + " AND datumIzdavanja = '" + datumIzdavanja + "'";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            Long idProfesor = rs.getLong("p.idProfesor");
            String imeProf = rs.getString("p.ime");
            String prezimeProf = rs.getString("p.prezime");
            String brTelProf = rs.getString("p.brojTelefona");
            String korIme = rs.getString("p.korisnickoIme");
            String sifraProf = rs.getString("p.sifra");
            Profesor p = new Profesor(idProfesor, imeProf, prezimeProf, brTelProf, korIme, sifraProf);

            Long idSertifikat = rs.getLong("s.idSertifikat");
            String institucija = rs.getString("s.institucija");
            String naziv = rs.getString("s.naziv");
            Sertifikat s = new Sertifikat(idSertifikat, institucija, naziv);

            Date datumIzdavanja = rs.getDate("ps.datumIzdavanja");

            lista.add(new PrS(datumIzdavanja, p, s));
        }
        return lista;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        if (rs.next()) {
            Long idProfesor = rs.getLong("p.idProfesor");
            String imeProf = rs.getString("p.ime");
            String prezimeProf = rs.getString("p.prezime");
            String brTelProf = rs.getString("p.brojTelefona");
            String korIme = rs.getString("p.korisnickoIme");
            String sifraProf = rs.getString("p.sifra");
            Profesor p = new Profesor(idProfesor, imeProf, prezimeProf, brTelProf, korIme, sifraProf);

            Long idSertifikat = rs.getLong("s.idSertifikat");
            String institucija = rs.getString("s.institucija");
            String naziv = rs.getString("s.naziv");
            Sertifikat s = new Sertifikat(idSertifikat, institucija, naziv);

            Date datumIzdavanja = rs.getDate("ps.datumIzdavanja");

            return new PrS(datumIzdavanja, p, s);
        }
        return null;
    }

    @Override
    public void postaviId(Long id) {
        // kompozitni kljuc, ne treba
    }
}
