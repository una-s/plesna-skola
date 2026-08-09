package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlesniNivo implements ApstraktniDomenskiObjekat {

    private Long idPlesniNivo;
    private String opis;
    private int nivo;

    public PlesniNivo() {
    }

    public PlesniNivo(Long idPlesniNivo, String opis, int nivo) {
        this.idPlesniNivo = idPlesniNivo;
        this.opis = opis;
        this.nivo = nivo;
    }

    public Long getIdPlesniNivo() {
        return idPlesniNivo;
    }

    public void setIdPlesniNivo(Long idPlesniNivo) {
        this.idPlesniNivo = idPlesniNivo;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getNivo() {
        return nivo;
    }

    public void setNivo(int nivo) {
        this.nivo = nivo;
    }

    @Override
    public String toString() {
        return opis + " " + nivo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(opis, nivo);
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
