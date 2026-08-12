package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja cas u plesnoj skoli.
 * 
 * Svaki cas ima naziv, trajanje i stil plesa koji se na njemu izvodi.
 * 
 * Implementira ApstraktniDomenskiObjekat za rad sa tabelom "cas"
 * u bazi podataka.
 * 
 * @author Una Stankovic
 */
public class Cas implements ApstraktniDomenskiObjekat {

    /**
     * Jedinstveni identifikator casa kao Long.
     */
    private Long idCas;

    /**
     * Naziv casa kao String.
     */
    private String naziv;

    /**
     * Trajanje casa u minutima kao ceo broj.
     */
    private int trajanje;

    /**
     * Stil plesa koji se izvodi na casu kao String.
     */
    private String stilPlesa;

    /**
     * Kreira objekat klase Cas sa podrazumevanim (null) vrednostima.
     */
    public Cas() {
    }

    /**
     * Kreira objekat klase Cas sa zadatim vrednostima.
     * 
     * @param idCas Jedinstveni identifikator casa.
     * @param naziv Naziv casa.
     * @param trajanje Trajanje casa u minutima.
     * @param stilPlesa Stil plesa koji se izvodi na casu.
     */
    public Cas(Long idCas, String naziv, int trajanje, String stilPlesa) {
        this.idCas = idCas;
        this.naziv = naziv;
        this.trajanje = trajanje;
        this.stilPlesa = stilPlesa;
    }

    /**
     * Vraca identifikator casa.
     * 
     * @return idCas casa kao Long.
     */
    public Long getIdCas() {
        return idCas;
    }

    /**
     * Postavlja identifikator casa na unetu vrednost.
     * 
     * @param idCas Novi identifikator casa.
     */
    public void setIdCas(Long idCas) {
        this.idCas = idCas;
    }

    /**
     * Vraca naziv casa.
     * 
     * @return naziv casa kao String.
     */
    public String getNaziv() {
        return naziv;
    }

    /**
     * Postavlja naziv casa na unetu vrednost.
     * 
     * @param naziv Novi naziv casa.
     */
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    /**
     * Vraca trajanje casa u minutima.
     * 
     * @return trajanje casa kao ceo broj.
     */
    public int getTrajanje() {
        return trajanje;
    }

    /**
     * Postavlja trajanje casa na unetu vrednost.
     * 
     * @param trajanje Novo trajanje casa u minutima.
     */
    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    /**
     * Vraca stil plesa koji se izvodi na casu.
     * 
     * @return stilPlesa kao String.
     */
    public String getStilPlesa() {
        return stilPlesa;
    }

    /**
     * Postavlja stil plesa na unetu vrednost.
     * 
     * @param stilPlesa Novi stil plesa.
     */
    public void setStilPlesa(String stilPlesa) {
        this.stilPlesa = stilPlesa;
    }

    /**
     * Vraca String reprezentaciju casa (naziv casa).
     * 
     * @return naziv casa kao String.
     */
    @Override
    public String toString() {
        return naziv;
    }

    /**
     * Vraca hash kod casa izracunat na osnovu naziva i stila plesa.
     * 
     * @return hash kod kao ceo broj.
     */
    @Override
    public int hashCode() {
        return Objects.hash(naziv, stilPlesa);
    }

    /**
     * Poredi dva casa po nazivu i stilu plesa.
     * 
     * @param obj Drugi objekat sa kojim se poredi.
     * @return 
     * <ul>
     * <li><b>true</b> - ako su oba objekta klase Cas sa istim nazivom
     * i stilom plesa ili ako su na istoj adresi.</li>
     * <li><b>false</b> - ako je drugi objekat null, ako je druge klase
     * ili ako nemaju isti naziv i stil plesa.</li>
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
        final Cas other = (Cas) obj;
        if (!Objects.equals(this.naziv, other.naziv)) {
            return false;
        }
        return Objects.equals(this.stilPlesa, other.stilPlesa);
    }

    @Override
    public String vratiNazivTabele() {
        return "cas";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        //nazivi moraju kao u bazi
        while (rs.next()) {
            Long casId = rs.getLong("c.idCas");
            String nazivCasa = rs.getString("c.naziv");
            int trajanjeCasa = rs.getInt("c.trajanje");
            String stilPlesaCasa = rs.getString("c.stilPlesa");
            Cas c = new Cas(casId, nazivCasa, trajanjeCasa, stilPlesaCasa);
            lista.add(c);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv,trajanje,stilPlesa";
    }

    @Override
    public String vratiVrednostZaUbacivanje() {
        return "'" + naziv + "', " + trajanje + ",'" + stilPlesa + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idCas=" + idCas;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        if (rs.next()) {
            Long casId = rs.getLong("c.idCas");
            String nazivCasa = rs.getString("c.naziv");
            int trajanjeCasa = rs.getInt("c.trajanje");
            String stilPlesaCasa = rs.getString("c.stilPlesa");
            return new Cas(casId, naziv, trajanje, stilPlesa);
        }
        return null;
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "naziv='" + naziv + "', trajanje=" + trajanje + ", stilPlesa='" + stilPlesa + "'";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public String alias() {
        return " c ";
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    @Override
    public void postaviId(Long id) {
        this.idCas = id;
    }
}