package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Una
 */
public class Sertifikat implements ApstraktniDomenskiObjekat {

    private Long idSertifikat;
    private String institucija;
    private String naziv;

    public Sertifikat() {
    }

    public Sertifikat(Long idSertifikat, String institucija, String naziv) {
        this.idSertifikat = idSertifikat;
        this.institucija = institucija;
        this.naziv = naziv;
    }

    public Long getIdSertifikat() {
        return idSertifikat;
    }

    public void setIdSertifikat(Long idSertifikat) {
        this.idSertifikat = idSertifikat;
    }

    public String getInstitucija() {
        return institucija;
    }

    public void setInstitucija(String institucija) {
        this.institucija = institucija;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
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
        final Sertifikat other = (Sertifikat) obj;
        if (!Objects.equals(this.institucija, other.institucija)) {
            return false;
        }
        return Objects.equals(this.naziv, other.naziv);
    }

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
