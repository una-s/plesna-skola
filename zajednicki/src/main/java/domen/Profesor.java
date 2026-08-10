package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja profesora u plesnoj skoli.
 * 
 * Svaki profesor ima ime, prezime, broj telefona, korisnicko ime i sifru
 * koji se koriste za prijavljivanje na sistem.
 * 
 * Implementira ApstraktniDomenskiObjekat za rad sa tabelom "profesor"
 * u bazi podataka.
 * 
 * @author Una Stankovic
 */
public class Profesor implements ApstraktniDomenskiObjekat {

    /**
     * Jedinstveni identifikator profesora kao Long.
     */
    private Long idProfesor;

    /**
     * Ime profesora kao String.
     */
    private String ime;

    /**
     * Prezime profesora kao String.
     */
    private String prezime;

    /**
     * Broj telefona profesora kao String.
     */
    private String brojTelefona;

    /**
     * Korisnicko ime profesora za prijavljivanje na sistem kao String.
     */
    private String korisnickoIme;

    /**
     * Sifra profesora za prijavljivanje na sistem kao String.
     */
    private String sifra;

    /**
     * Kreira objekat klase Profesor sa podrazumevanim (null) vrednostima.
     */
    public Profesor() {
    }

    /**
     * Kreira objekat klase Profesor sa zadatim vrednostima.
     * 
     * @param idProfesor Jedinstveni identifikator profesora.
     * @param ime Ime profesora.
     * @param prezime Prezime profesora.
     * @param brojTelefona Broj telefona profesora.
     * @param korisnickoIme Korisnicko ime za prijavljivanje.
     * @param sifra Sifra za prijavljivanje.
     */
    public Profesor(Long idProfesor, String ime, String prezime, String brojTelefona, String korisnickoIme, String sifra) {
        this.idProfesor = idProfesor;
        this.ime = ime;
        this.prezime = prezime;
        this.brojTelefona = brojTelefona;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
    }

    /**
     * Vraca identifikator profesora.
     * 
     * @return idProfesor kao Long.
     */
    public Long getIdProfesor() {
        return idProfesor;
    }

    /**
     * Postavlja identifikator profesora na unetu vrednost.
     * 
     * @param idProfesor Novi identifikator profesora.
     */
    public void setIdProfesor(Long idProfesor) {
        this.idProfesor = idProfesor;
    }

    /**
     * Vraca ime profesora.
     * 
     * @return ime profesora kao String.
     */
    public String getIme() {
        return ime;
    }

    /**
     * Postavlja ime profesora na unetu vrednost.
     * 
     * @param ime Novo ime profesora.
     */
    public void setIme(String ime) {
        this.ime = ime;
    }

    /**
     * Vraca prezime profesora.
     * 
     * @return prezime profesora kao String.
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Postavlja prezime profesora na unetu vrednost.
     * 
     * @param prezime Novo prezime profesora.
     */
    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    /**
     * Vraca broj telefona profesora.
     * 
     * @return brojTelefona kao String.
     */
    public String getBrojTelefona() {
        return brojTelefona;
    }

    /**
     * Postavlja broj telefona profesora na unetu vrednost.
     * 
     * @param brojTelefona Novi broj telefona profesora.
     */
    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    /**
     * Vraca korisnicko ime profesora.
     * 
     * @return korisnickoIme kao String.
     */
    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    /**
     * Postavlja korisnicko ime profesora na unetu vrednost.
     * 
     * @param korisnickoIme Novo korisnicko ime profesora.
     */
    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    /**
     * Vraca sifru profesora.
     * 
     * @return sifra kao String.
     */
    public String getSifra() {
        return sifra;
    }

    /**
     * Postavlja sifru profesora na unetu vrednost.
     * 
     * @param sifra Nova sifra profesora.
     */
    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    /**
     * Vraca String reprezentaciju profesora (ime i prezime).
     * 
     * @return ime i prezime profesora u formatu "ime prezime".
     */
    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    /**
     * Vraca hash kod profesora izracunat na osnovu korisnickog imena i sifre.
     * 
     * @return hash kod kao ceo broj.
     */
    @Override
    public int hashCode() {
        return Objects.hash(korisnickoIme, sifra);
    }

    /**
     * Poredi dva profesora po korisnickom imenu i sifri.
     * 
     * @param obj Drugi objekat sa kojim se poredi.
     * @return 
     * <ul>
     * <li><b>true</b> - ako su oba objekta klase Profesor sa istim
     * korisnickim imenom i sifrom ili ako su na istoj adresi.</li>
     * <li><b>false</b> - ako je drugi objekat null, ako je druge klase
     * ili ako nemaju isto korisnicko ime i sifru.</li>
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

    /**
     * Vraca uslov za pretragu profesora na osnovu korisnickog imena i sifre.
     * 
     * Uslov se gradi od popunjenih polja - korisnickog imena i sifre,
     * sa tacnim poklapanjem (koristi se prilikom prijavljivanja na sistem).
     * 
     * @return uslov za pretragu kao String ili prazan String ako
     * nijedno polje nije popunjeno.
     */
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