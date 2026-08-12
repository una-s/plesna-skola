package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja sertifikat koji profesor moze da stekne.
 * 
 * Svaki sertifikat ima instituciju koja ga izdaje i naziv.
 * 
 * Implementira ApstraktniDomenskiObjekat za rad sa tabelom "sertifikat"
 * u bazi podataka.
 * 
 * @author Una Stankovic
 */
public class Sertifikat implements ApstraktniDomenskiObjekat {

    /**
     * Jedinstveni identifikator sertifikata kao Long.
     */
    private Long idSertifikat;

    /**
     * Institucija koja izdaje sertifikat kao String.
     */
    private String institucija;

    /**
     * Naziv sertifikata kao String.
     */
    private String naziv;

    /**
     * Kreira objekat klase Sertifikat sa podrazumevanim (null) vrednostima.
     */
    public Sertifikat() {
    }

    /**
     * Kreira objekat klase Sertifikat sa zadatim vrednostima.
     * 
     * @param idSertifikat Jedinstveni identifikator sertifikata.
     * @param institucija Institucija koja izdaje sertifikat.
     * @param naziv Naziv sertifikata.
     */
    public Sertifikat(Long idSertifikat, String institucija, String naziv) {
        this.idSertifikat = idSertifikat;
        this.institucija = institucija;
        this.naziv = naziv;
    }

    /**
     * Vraca identifikator sertifikata.
     * 
     * @return idSertifikat kao Long.
     */
    public Long getIdSertifikat() {
        return idSertifikat;
    }

    /**
     * Postavlja identifikator sertifikata na unetu vrednost.
     * 
     * @param idSertifikat Novi identifikator sertifikata.
     */
    public void setIdSertifikat(Long idSertifikat) {
        this.idSertifikat = idSertifikat;
    }

    /**
     * Vraca instituciju koja izdaje sertifikat.
     * 
     * @return institucija kao String.
     */
    public String getInstitucija() {
        return institucija;
    }

    /**
     * Postavlja instituciju na unetu vrednost.
     * 
     * @param institucija Nova institucija koja izdaje sertifikat.
     */
    public void setInstitucija(String institucija) {
        this.institucija = institucija;
    }

    /**
     * Vraca naziv sertifikata.
     * 
     * @return naziv kao String.
     */
    public String getNaziv() {
        return naziv;
    }

    /**
     * Postavlja naziv sertifikata na unetu vrednost.
     * 
     * @param naziv Novi naziv sertifikata.
     */
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    /**
     * Vraca hash kod sertifikata izracunat na osnovu institucije i naziva.
     * 
     * @return hash kod kao ceo broj.
     */
    @Override
    public int hashCode() {
        return Objects.hash(institucija, naziv);
    }

    /**
     * Poredi dva sertifikata po instituciji i nazivu.
     * 
     * @param obj Drugi objekat sa kojim se poredi.
     * @return 
     * <ul>
     * <li><b>true</b> - ako su oba objekta klase Sertifikat sa istom
     * institucijom i nazivom ili ako su na istoj adresi.</li>
     * <li><b>false</b> - ako je drugi objekat null, ako je druge klase
     * ili ako nemaju istu instituciju i naziv.</li>
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
        final Sertifikat other = (Sertifikat) obj;
        if (!Objects.equals(this.institucija, other.institucija)) {
            return false;
        }
        return Objects.equals(this.naziv, other.naziv);
    }

    /**
     * Vraca String reprezentaciju sertifikata (naziv i institucija).
     * 
     * @return podaci o sertifikatu u formatu "naziv institucija".
     */
    @Override
    public String toString() {
        return naziv + " " + institucija;
    }

    @Override
    public String vratiNazivTabele() {
        return "sertifikat";
    }

    @Override
    public String alias() {
        return " s ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "institucija, naziv";
    }

    @Override
    public String vratiVrednostZaUbacivanje() {
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(institucija).append("', '")
                .append(naziv).append("'");
        return sb.toString();
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("institucija = '").append(institucija).append("', ")
                .append("naziv = '").append(naziv).append("'");
        return sb.toString();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idSertifikat = " + idSertifikat;
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            Long idSertifikat = rs.getLong("s.idSertifikat");
            String institucija = rs.getString("s.institucija");
            String naziv = rs.getString("s.naziv");
            lista.add(new Sertifikat(idSertifikat, institucija, naziv));
        }
        return lista;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        if (rs.next()) {
            Long idSertifikat = rs.getLong("s.idSertifikat");
            String institucija = rs.getString("s.institucija");
            String naziv = rs.getString("s.naziv");
            return new Sertifikat(idSertifikat, institucija, naziv);
        }
        return null;
    }

    @Override
    public void postaviId(Long id) {
        this.idSertifikat = id;
    }
}