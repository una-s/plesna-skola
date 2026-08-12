package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja vezu izmedju profesora i sertifikata koji je profesor stekao.
 * 
 * Svaka veza sadrzi profesora, sertifikat i datum izdavanja sertifikata.
 * Koristi kompozitni primarni kljuc (profesor, sertifikat, datumIzdavanja).
 * 
 * Implementira ApstraktniDomenskiObjekat za rad sa tabelom "pr_s"
 * u bazi podataka.
 * 
 * @author Una Stankovic
 */
public class PrS implements ApstraktniDomenskiObjekat {

    /**
     * Datum izdavanja sertifikata profesoru.
     */
    private Date datumIzdavanja;

    /**
     * Profesor koji je stekao sertifikat.
     */
    private Profesor profesor;

    /**
     * Sertifikat koji je profesor stekao.
     */
    private Sertifikat sertifikat;

    /**
     * Kreira objekat klase PrS sa podrazumevanim (null) vrednostima.
     */
    public PrS() {
    }

    /**
     * Kreira objekat klase PrS sa zadatim vrednostima.
     * 
     * @param datumIzdavanja Datum izdavanja sertifikata.
     * @param profesor Profesor koji je stekao sertifikat.
     * @param sertifikat Sertifikat koji je profesor stekao.
     */
    public PrS(Date datumIzdavanja, Profesor profesor, Sertifikat sertifikat) {
        this.datumIzdavanja = datumIzdavanja;
        this.profesor = profesor;
        this.sertifikat = sertifikat;
    }

    /**
     * Vraca datum izdavanja sertifikata.
     * 
     * @return datumIzdavanja kao Date.
     */
    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    /**
     * Postavlja datum izdavanja sertifikata na unetu vrednost.
     * 
     * @param datumIzdavanja Novi datum izdavanja.
     */
    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    /**
     * Vraca profesora koji je stekao sertifikat.
     * 
     * @return profesor kao objekat klase Profesor.
     */
    public Profesor getProfesor() {
        return profesor;
    }

    /**
     * Postavlja profesora na unetu vrednost.
     * 
     * @param profesor Novi profesor.
     */
    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    /**
     * Vraca sertifikat koji je profesor stekao.
     * 
     * @return sertifikat kao objekat klase Sertifikat.
     */
    public Sertifikat getSertifikat() {
        return sertifikat;
    }

    /**
     * Postavlja sertifikat na unetu vrednost.
     * 
     * @param sertifikat Novi sertifikat.
     */
    public void setSertifikat(Sertifikat sertifikat) {
        this.sertifikat = sertifikat;
    }

    /**
     * Vraca String reprezentaciju veze (profesor, sertifikat, datum).
     * 
     * @return podaci o vezi u formatu "profesor sertifikat datumIzdavanja".
     */
    @Override
    public String toString() {
        return profesor + " " + sertifikat + " " + datumIzdavanja;
    }

    /**
     * Vraca hash kod veze izracunat na osnovu datuma izdavanja,
     * profesora i sertifikata.
     * 
     * @return hash kod kao ceo broj.
     */
    @Override
    public int hashCode() {
        return Objects.hash(datumIzdavanja, profesor, sertifikat);
    }

    /**
     * Poredi dve veze po datumu izdavanja, profesoru i sertifikatu.
     * 
     * @param obj Drugi objekat sa kojim se poredi.
     * @return 
     * <ul>
     * <li><b>true</b> - ako su oba objekta klase PrS sa istim datumom
     * izdavanja, profesorom i sertifikatom ili ako su na istoj adresi.</li>
     * <li><b>false</b> - ako je drugi objekat null, ako je druge klase
     * ili ako nemaju iste vrednosti sva tri polja.</li>
     * </ul>
     */
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