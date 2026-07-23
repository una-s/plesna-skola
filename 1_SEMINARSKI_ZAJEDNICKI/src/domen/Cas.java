/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Una
 */
public class Cas implements ApstraktniDomenskiObjekat {

    private Long idCas;
    private String naziv;
    private int trajanje;
    private String stilPlesa;

    public Cas() {
    }

    public Cas(Long idCas, String naziv, int trajanje, String stilPlesa) {
        this.idCas = idCas;
        this.naziv = naziv;
        this.trajanje = trajanje;
        this.stilPlesa = stilPlesa;
    }

    public Long getIdCas() {
        return idCas;
    }

    public void setIdCas(Long idCas) {
        this.idCas = idCas;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    public String getStilPlesa() {
        return stilPlesa;
    }

    public void setStilPlesa(String stilPlesa) {
        this.stilPlesa = stilPlesa;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
