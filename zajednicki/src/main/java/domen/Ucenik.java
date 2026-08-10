package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja ucenika plesne skole.
 * 
 * Svaki ucenik ima ime, prezime, broj telefona i plesni nivo koji pohadja.
 * 
 * Implementira ApstraktniDomenskiObjekat za rad sa tabelom "ucenik"
 * u bazi podataka.
 * 
 * @author Una Stankovic
 */
public class Ucenik implements ApstraktniDomenskiObjekat {

    /**
     * Jedinstveni identifikator ucenika kao Long.
     */
    private Long idUcenik;

    /**
     * Ime ucenika kao String.
     */
    private String ime;

    /**
     * Prezime ucenika kao String.
     */
    private String prezime;

    /**
     * Broj telefona ucenika kao String.
     */
    private String brojTelefona;

    /**
     * Plesni nivo koji ucenik pohadja.
     */
    private PlesniNivo plesniNivo;

    /**
     * Kreira objekat klase Ucenik sa podrazumevanim (null) vrednostima.
     */
    public Ucenik() {
    }

    /**
     * Kreira objekat klase Ucenik sa zadatim vrednostima.
     * 
     * @param idUcenik Jedinstveni identifikator ucenika.
     * @param ime Ime ucenika.
     * @param prezime Prezime ucenika.
     * @param brojTelefona Broj telefona ucenika.
     * @param plesniNivo Plesni nivo koji ucenik pohadja.
     */
    public Ucenik(Long idUcenik, String ime, String prezime, String brojTelefona, PlesniNivo plesniNivo) {
        this.idUcenik = idUcenik;
        this.ime = ime;
        this.prezime = prezime;
        this.brojTelefona = brojTelefona;
        this.plesniNivo = plesniNivo;
    }

    /**
     * Vraca identifikator ucenika.
     * 
     * @return idUcenik kao Long.
     */
    public Long getIdUcenik() {
        return idUcenik;
    }

    /**
     * Postavlja identifikator ucenika na unetu vrednost.
     * 
     * @param idUcenik Novi identifikator ucenika.
     */
    public void setIdUcenik(Long idUcenik) {
        this.idUcenik = idUcenik;
    }

    /**
     * Vraca ime ucenika.
     * 
     * @return ime ucenika kao String.
     */
    public String getIme() {
        return ime;
    }

    /**
     * Postavlja ime ucenika na unetu vrednost.
     * 
     * @param ime Novo ime ucenika.
     */
    public void setIme(String ime) {
        this.ime = ime;
    }

    /**
     * Vraca prezime ucenika.
     * 
     * @return prezime ucenika kao String.
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Postavlja prezime ucenika na unetu vrednost.
     * 
     * @param prezime Novo prezime ucenika.
     */
    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    /**
     * Vraca broj telefona ucenika.
     * 
     * @return brojTelefona kao String.
     */
    public String getBrojTelefona() {
        return brojTelefona;
    }

    /**
     * Postavlja broj telefona ucenika na unetu vrednost.
     * 
     * @param brojTelefona Novi broj telefona ucenika.
     */
    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    /**
     * Vraca plesni nivo koji ucenik pohadja.
     * 
     * @return plesniNivo kao objekat klase PlesniNivo.
     */
    public PlesniNivo getPlesniNivo() {
        return plesniNivo;
    }

    /**
     * Postavlja plesni nivo ucenika na unetu vrednost.
     * 
     * @param plesniNivo Novi plesni nivo ucenika.
     */
    public void setPlesniNivo(PlesniNivo plesniNivo) {
        this.plesniNivo = plesniNivo;
    }

    /**
     * Vraca String reprezentaciju ucenika (ime i prezime).
     * 
     * @return ime i prezime ucenika u formatu "ime prezime".
     */
    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    /**
     * Vraca hash kod ucenika izracunat na osnovu broja telefona.
     * 
     * @return hash kod kao ceo broj.
     */
    @Override
    public int hashCode() {
        return Objects.hash(brojTelefona);
    }

    /**
     * Poredi dva ucenika po broju telefona.
     * 
     * @param obj Drugi objekat sa kojim se poredi.
     * @return 
     * <ul>
     * <li><b>true</b> - ako su oba objekta klase Ucenik sa istim
     * brojem telefona ili ako su na istoj adresi.</li>
     * <li><b>false</b> - ako je drugi objekat null, ako je druge klase
     * ili ako nemaju isti broj telefona.</li>
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
        final Ucenik other = (Ucenik) obj;
        return Objects.equals(this.brojTelefona, other.brojTelefona);
    }

    @Override
    public String vratiNazivTabele() {
        return "ucenik";
    }

    @Override
    public String alias() {
        return " u ";
    }

    @Override
    public String join() {
        return " JOIN plesni_nivo pn ON u.idPlesniNivo = pn.idPlesniNivo ";
    }

    /**
     * Vraca uslov za pretragu ucenika na osnovu popunjenih polja.
     * 
     * Za ime i prezime koristi se delimicno poklapanje (LIKE '%...%'),
     * dok se za plesni nivo i broj telefona koristi tacno poklapanje.
     * 
     * @return uslov za pretragu kao String ili prazan String ako
     * nijedno polje nije popunjeno.
     */
    @Override
    public String uslovZaSelect() {
        StringBuilder sb = new StringBuilder();

        if (ime != null && !ime.isEmpty()) {
            sb.append("u.ime LIKE '%").append(ime).append("%'");
        }
        if (prezime != null && !prezime.isEmpty()) {
            if (sb.length() > 0) {
                sb.append(" AND ");
            }
            sb.append("u.prezime LIKE '%").append(prezime).append("%'");
        }
        if (plesniNivo != null) {
            if (sb.length() > 0) {
                sb.append(" AND ");
            }
            sb.append("u.idPlesniNivo = ").append(plesniNivo.getIdPlesniNivo());
        }
        if (brojTelefona != null && !brojTelefona.isEmpty()) {
            if (sb.length() > 0) {
                sb.append(" AND ");
            }
            sb.append("u.brojTelefona = '").append(brojTelefona).append("'");
        }

        return sb.toString();
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime, prezime, brojTelefona, idPlesniNivo";
    }

    @Override
    public String vratiVrednostZaUbacivanje() {
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(ime).append("', ")
                .append("'").append(prezime).append("', ")
                .append("'").append(brojTelefona).append("', ")
                .append(plesniNivo.getIdPlesniNivo());
        return sb.toString();
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("ime = '").append(ime).append("', ")
                .append("prezime = '").append(prezime).append("', ")
                .append("brojTelefona = '").append(brojTelefona).append("', ")
                .append("idPlesniNivo = ").append(plesniNivo.getIdPlesniNivo());
        return sb.toString();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idUcenik = " + idUcenik;
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            Long idUcenik = rs.getLong("u.idUcenik");
            String ime = rs.getString("u.ime");
            String prezime = rs.getString("u.prezime");
            String brTel = rs.getString("u.brojTelefona");

            Long idPlesniNivo = rs.getLong("pn.idPlesniNivo");
            String opis = rs.getString("pn.opis");
            int nivo = rs.getInt("pn.nivo");

            PlesniNivo pn = new PlesniNivo(idPlesniNivo, opis, nivo);

            lista.add(new Ucenik(idUcenik, ime, prezime, brTel, pn));
        }
        return lista;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        if (rs.next()) {
            Long idUcenik = rs.getLong("u.idUcenik");
            String ime = rs.getString("u.ime");
            String prezime = rs.getString("u.prezime");
            String brTel = rs.getString("u.brojTelefona");

            Long idPlesniNivo = rs.getLong("pn.idPlesniNivo");
            String opis = rs.getString("pn.opis");
            int nivo = rs.getInt("pn.nivo");

            PlesniNivo pn = new PlesniNivo(idPlesniNivo, opis, nivo);

            return new Ucenik(idUcenik, ime, prezime, brTel, pn);
        }
        return null;
    }

    @Override
    public void postaviId(Long id) {
        this.idUcenik = id;
    }
}