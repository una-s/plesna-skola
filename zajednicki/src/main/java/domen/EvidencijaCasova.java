package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class EvidencijaCasova implements ApstraktniDomenskiObjekat {

    private Long idEvidencijaCasova;
    private String skolskaGodina;
    private Date datumPocetka;
    private Date datumZavrsetka;
    private int trajanjeEvidencije;
    private double prosecnaOcena;
    private int brojPrisustva;
    private Profesor profesor;
    private Ucenik ucenik;
    private List<StavkaEvidencijeCasova> stavke;

    public EvidencijaCasova() {
    }

    public EvidencijaCasova(Long idEvidencijaCasova, String skolskaGodina, Date datumPocetka, Date datumZavrsetka,
            int trajanjeEvidencije, double prosecnaOcena, int brojPrisustva,
            Profesor profesor, Ucenik ucenik, List<StavkaEvidencijeCasova> stavke) {
        this.idEvidencijaCasova = idEvidencijaCasova;
        this.skolskaGodina = skolskaGodina;
        this.datumPocetka = datumPocetka;
        this.datumZavrsetka = datumZavrsetka;
        this.trajanjeEvidencije = trajanjeEvidencije;
        this.prosecnaOcena = prosecnaOcena;
        this.brojPrisustva = brojPrisustva;
        this.profesor = profesor;
        this.ucenik = ucenik;
        this.stavke = stavke;
    }

    public Long getIdEvidencijaCasova() {
        return idEvidencijaCasova;
    }

    public void setIdEvidencijaCasova(Long idEvidencijaCasova) {
        this.idEvidencijaCasova = idEvidencijaCasova;
    }

    public String getSkolskaGodina() {
        return skolskaGodina;
    }

    public void setSkolskaGodina(String skolskaGodina) {
        this.skolskaGodina = skolskaGodina;
    }

    public Date getDatumPocetka() {
        return datumPocetka;
    }

    public void setDatumPocetka(Date datumPocetka) {
        this.datumPocetka = datumPocetka;
    }

    public Date getDatumZavrsetka() {
        return datumZavrsetka;
    }

    public void setDatumZavrsetka(Date datumZavrsetka) {
        this.datumZavrsetka = datumZavrsetka;
    }

    public int getTrajanjeEvidencije() {
        return trajanjeEvidencije;
    }

    public void setTrajanjeEvidencije(int trajanjeEvidencije) {
        this.trajanjeEvidencije = trajanjeEvidencije;
    }

    public double getProsecnaOcena() {
        return prosecnaOcena;
    }

    public void setProsecnaOcena(double prosecnaOcena) {
        this.prosecnaOcena = prosecnaOcena;
    }

    public int getBrojPrisustva() {
        return brojPrisustva;
    }

    public void setBrojPrisustva(int brojPrisustva) {
        this.brojPrisustva = brojPrisustva;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Ucenik getUcenik() {
        return ucenik;
    }

    public void setUcenik(Ucenik ucenik) {
        this.ucenik = ucenik;
    }

    public List<StavkaEvidencijeCasova> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaEvidencijeCasova> stavke) {
        this.stavke = stavke;
    }

    @Override
    public String toString() {
        return "Skolska godina: " + skolskaGodina + ", Ucenik: " + ucenik + ", Profesor: " + profesor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final EvidencijaCasova other = (EvidencijaCasova) obj;
        return this.idEvidencijaCasova == other.idEvidencijaCasova;
    }

    @Override
    public String vratiNazivTabele() {
        return "evidencija_casova";
    }

    @Override
    public String alias() {
        return " ec ";
    }

    @Override
    public String join() {
        return "JOIN profesor p ON ec.profesor = p.idProfesor "
                + "JOIN ucenik u ON ec.ucenik = u.idUcenik "
                + "JOIN plesni_nivo pn ON pn.idPlesniNivo = u.idPlesniNivo ";
    }

    @Override
    public String vratiVrednostZaUbacivanje() {
        return "'" + skolskaGodina + "', '"
                + new java.sql.Date(datumPocetka.getTime()) + "', '"
                + new java.sql.Date(datumZavrsetka.getTime()) + "', "
                + profesor.getIdProfesor() + ", "
                + ucenik.getIdUcenik();
        // Izbačeni: prosecnaOcena, brojPrisustva, trajanjeEvidencije
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "skolskaGodina = '" + skolskaGodina + "', "
                + "datumPocetka = '" + new java.sql.Date(datumPocetka.getTime()) + "', "
                + "datumZavrsetka = '" + new java.sql.Date(datumZavrsetka.getTime()) + "', "
                + "profesor = " + profesor.getIdProfesor() + ", "
                + "ucenik = " + ucenik.getIdUcenik();
        // Izbačeni: prosecnaOcena, brojPrisustva, trajanjeEvidencije
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "skolskaGodina, datumPocetka, datumZavrsetka, profesor, ucenik";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idEvidencijaCasova = " + idEvidencijaCasova;
    }

    @Override
    public String uslovZaSelect() {

        if (idEvidencijaCasova != null && idEvidencijaCasova > 0) {
            return "ec.idEvidencijaCasova = " + idEvidencijaCasova;
        }

        StringBuilder sb = new StringBuilder();
        if (profesor != null) {
            sb.append("ec.profesor = ").append(profesor.getIdProfesor());
        }
        if (ucenik != null) {
            if (sb.length() > 0) {
                sb.append(" AND ");
            }
            sb.append("ec.ucenik = ").append(ucenik.getIdUcenik());
        }
        if (skolskaGodina != null && !skolskaGodina.isEmpty()) {
            if (sb.length() > 0) {
                sb.append(" AND ");
            }
            sb.append("ec.skolskaGodina = '").append(skolskaGodina).append("'");
        }
        return sb.toString();
    }

    private EvidencijaCasova ocitajIzRS(ResultSet rs) throws Exception {
        Long idEvidencija = rs.getLong("ec.idEvidencijaCasova");
        String skolskaGod = rs.getString("ec.skolskaGodina");
        Date datPocetka = rs.getDate("ec.datumPocetka");
        Date datZavrsetka = rs.getDate("ec.datumZavrsetka");
        int trajanje = rs.getInt("ec.trajanjeEvidencije");
        double prosOcena = rs.getDouble("ec.prosecnaOcena");
        int brPrisustva = rs.getInt("ec.brojPrisustva");

        Long idProfesor = rs.getLong("p.idProfesor");
        String imeProf = rs.getString("p.ime");
        String prezimeProf = rs.getString("p.prezime");
        String brTelProf = rs.getString("p.brojTelefona");
        String korIme = rs.getString("p.korisnickoIme");
        String sifraProf = rs.getString("p.sifra");
        Profesor profesor = new Profesor(idProfesor, imeProf, prezimeProf, brTelProf, korIme, sifraProf);

        Long idPlesniNivo = rs.getLong("pn.idPlesniNivo");
        String opis = rs.getString("pn.opis");
        int nivo = rs.getInt("pn.nivo");
        PlesniNivo plesniNivo = new PlesniNivo(idPlesniNivo, opis, nivo);

        Long idUcenik = rs.getLong("u.idUcenik");
        String imeUcenik = rs.getString("u.ime");
        String prezimeUcenik = rs.getString("u.prezime");
        String brTelUcenik = rs.getString("u.brojTelefona");
        Ucenik ucenik = new Ucenik(idUcenik, imeUcenik, prezimeUcenik, brTelUcenik, plesniNivo);

        return new EvidencijaCasova(idEvidencija, skolskaGod, datPocetka, datZavrsetka,
                trajanje, prosOcena, brPrisustva, profesor, ucenik, null);
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            lista.add(ocitajIzRS(rs));
        }
        return lista;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        if (rs.next()) {
            return ocitajIzRS(rs);
        }
        return null;
    }

    @Override
    public void postaviId(Long id) {
        this.idEvidencijaCasova = id;
    }
}
