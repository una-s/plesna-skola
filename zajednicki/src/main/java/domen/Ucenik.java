package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Una
 */
public class Ucenik implements ApstraktniDomenskiObjekat {

    private Long idUcenik;
    private String ime;
    private String prezime;
    private String brojTelefona;
    private PlesniNivo plesniNivo;

    public Ucenik() {
    }

    public Ucenik(Long idUcenik, String ime, String prezime, String brojTelefona, PlesniNivo plesniNivo) {
        this.idUcenik = idUcenik;
        this.ime = ime;
        this.prezime = prezime;
        this.brojTelefona = brojTelefona;
        this.plesniNivo = plesniNivo;
    }

    public Long getIdUcenik() {
        return idUcenik;
    }

    public void setIdUcenik(Long idUcenik) {
        this.idUcenik = idUcenik;
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

    public PlesniNivo getPlesniNivo() {
        return plesniNivo;
    }

    public void setPlesniNivo(PlesniNivo plesniNivo) {
        this.plesniNivo = plesniNivo;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(brojTelefona);
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
