package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja plesni nivo koji ucenik moze da ima u plesnoj skoli.
 * 
 * Svaki plesni nivo ima opis i redni broj nivoa.
 * 
 * Implementira ApstraktniDomenskiObjekat za rad sa tabelom
 * "plesni_nivo" u bazi podataka.
 * 
 * @author Una Stankovic
 */
public class PlesniNivo implements ApstraktniDomenskiObjekat {

    /**
     * Jedinstveni identifikator plesnog nivoa kao Long.
     */
    private Long idPlesniNivo;

    /**
     * Opis plesnog nivoa kao String.
     */
    private String opis;

    /**
     * Redni broj nivoa kao ceo broj.
     */
    private int nivo;

    /**
     * Kreira objekat klase PlesniNivo sa podrazumevanim (null) vrednostima.
     */
    public PlesniNivo() {
    }

    /**
     * Kreira objekat klase PlesniNivo sa zadatim vrednostima.
     * 
     * @param idPlesniNivo Jedinstveni identifikator plesnog nivoa.
     * @param opis Opis plesnog nivoa.
     * @param nivo Redni broj nivoa.
     */
    public PlesniNivo(Long idPlesniNivo, String opis, int nivo) {
        this.idPlesniNivo = idPlesniNivo;
        this.opis = opis;
        this.nivo = nivo;
    }

    /**
     * Vraca identifikator plesnog nivoa.
     * 
     * @return idPlesniNivo kao Long.
     */
    public Long getIdPlesniNivo() {
        return idPlesniNivo;
    }

    /**
     * Postavlja identifikator plesnog nivoa na unetu vrednost.
     * 
     * @param idPlesniNivo Novi identifikator plesnog nivoa.
     */
    public void setIdPlesniNivo(Long idPlesniNivo) {
        this.idPlesniNivo = idPlesniNivo;
    }

    /**
     * Vraca opis plesnog nivoa.
     * 
     * @return opis kao String.
     */
    public String getOpis() {
        return opis;
    }

    /**
     * Postavlja opis plesnog nivoa na unetu vrednost.
     * 
     * @param opis Novi opis plesnog nivoa.
     */
    public void setOpis(String opis) {
        this.opis = opis;
    }

    /**
     * Vraca redni broj nivoa.
     * 
     * @return nivo kao ceo broj.
     */
    public int getNivo() {
        return nivo;
    }

    /**
     * Postavlja redni broj nivoa na unetu vrednost.
     * 
     * @param nivo Novi redni broj nivoa.
     */
    public void setNivo(int nivo) {
        this.nivo = nivo;
    }

    /**
     * Vraca String reprezentaciju plesnog nivoa (opis i nivo).
     * 
     * @return podaci o plesnom nivou u formatu "opis nivo".
     */
    @Override
    public String toString() {
        return opis + " " + nivo;
    }

    /**
     * Vraca hash kod plesnog nivoa izracunat na osnovu opisa i nivoa.
     * 
     * @return hash kod kao ceo broj.
     */
    @Override
    public int hashCode() {
        return Objects.hash(opis, nivo);
    }

    /**
     * Poredi dva plesna nivoa po opisu i rednom broju nivoa.
     * 
     * @param obj Drugi objekat sa kojim se poredi.
     * @return 
     * <ul>
     * <li><b>true</b> - ako su oba objekta klase PlesniNivo sa istim
     * opisom i nivoom ili ako su na istoj adresi.</li>
     * <li><b>false</b> - ako je drugi objekat null, ako je druge klase
     * ili ako nemaju isti opis i nivo.</li>
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
        final PlesniNivo other = (PlesniNivo) obj;
        if (this.nivo != other.nivo) {
            return false;
        }
        return Objects.equals(this.opis, other.opis);
    }

    @Override
    public String vratiNazivTabele() {
        return "plesni_nivo";
    }

    @Override
    public String alias() {
        return " pn ";
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
        return "opis, nivo";
    }

    @Override
    public String vratiVrednostZaUbacivanje() {
        return "'" + opis + "', " + nivo;
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "opis = '" + opis + "', nivo = " + nivo;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idPlesniNivo = " + idPlesniNivo;
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            Long idPlesniNivo = rs.getLong("pn.idPlesniNivo");
            String opis = rs.getString("pn.opis");
            int nivo = rs.getInt("pn.nivo");;
            lista.add(new PlesniNivo(idPlesniNivo, opis, nivo));
        }
        return lista;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        if (rs.next()) {
            Long idPlesniNivo = rs.getLong("pn.idPlesniNivo");
            String opis = rs.getString("pn.opis");
            int nivo = rs.getInt("pn.nivo");
            return new PlesniNivo(idPlesniNivo, opis, nivo);
        }
        return null;
    }

    @Override
    public void postaviId(Long id) {
        this.idPlesniNivo = id;
    }
}