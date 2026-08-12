package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja evidenciju casova jednog ucenika kod jednog profesora
 * za odredjenu skolsku godinu.
 * 
 * Sadrzi podatke o skolskoj godini, datumima pocetka i zavrsetka,
 * profesoru, uceniku i listi stavki evidencije (pojedinacnih casova).
 * 
 * Implementira ApstraktniDomenskiObjekat za rad sa tabelom
 * "evidencija_casova" u bazi podataka.
 * 
 * @author Una Stankovic
 */
public class EvidencijaCasova implements ApstraktniDomenskiObjekat {

    /**
     * Jedinstveni identifikator evidencije casova kao Long.
     */
    private Long idEvidencijaCasova;

    /**
     * Skolska godina za koju se vodi evidencija, u formatu "xxxx/xxxx".
     */
    private String skolskaGodina;

    /**
     * Datum pocetka evidencije.
     */
    private Date datumPocetka;

    /**
     * Datum zavrsetka evidencije.
     */
    private Date datumZavrsetka;

    /**
     * Trajanje evidencije kao ceo broj.
     */
    private int trajanjeEvidencije;

    /**
     * Prosecna ocena ucenika u okviru evidencije kao decimalni broj.
     */
    private double prosecnaOcena;

    /**
     * Broj prisustava ucenika u okviru evidencije kao ceo broj.
     */
    private int brojPrisustva;

    /**
     * Profesor koji vodi evidenciju.
     */
    private Profesor profesor;

    /**
     * Ucenik na koga se evidencija odnosi.
     */
    private Ucenik ucenik;

    /**
     * Lista stavki evidencije (pojedinacnih casova sa ocenama i prisustvom).
     */
    private List<StavkaEvidencijeCasova> stavke;

    /**
     * Kreira objekat klase EvidencijaCasova sa podrazumevanim (null)
     * vrednostima.
     */
    public EvidencijaCasova() {
    }

    /**
     * Kreira objekat klase EvidencijaCasova sa zadatim vrednostima.
     * 
     * @param idEvidencijaCasova Jedinstveni identifikator evidencije.
     * @param skolskaGodina Skolska godina u formatu "xxxx/xxxx".
     * @param datumPocetka Datum pocetka evidencije.
     * @param datumZavrsetka Datum zavrsetka evidencije.
     * @param trajanjeEvidencije Trajanje evidencije.
     * @param prosecnaOcena Prosecna ocena ucenika.
     * @param brojPrisustva Broj prisustava ucenika.
     * @param profesor Profesor koji vodi evidenciju.
     * @param ucenik Ucenik na koga se evidencija odnosi.
     * @param stavke Lista stavki evidencije.
     */
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

    /**
     * Vraca identifikator evidencije casova.
     * 
     * @return idEvidencijaCasova kao Long.
     */
    public Long getIdEvidencijaCasova() {
        return idEvidencijaCasova;
    }

    /**
     * Postavlja identifikator evidencije casova na unetu vrednost.
     * 
     * @param idEvidencijaCasova Novi identifikator evidencije.
     */
    public void setIdEvidencijaCasova(Long idEvidencijaCasova) {
        this.idEvidencijaCasova = idEvidencijaCasova;
    }

    /**
     * Vraca skolsku godinu evidencije.
     * 
     * @return skolskaGodina kao String u formatu "xxxx/xxxx".
     */
    public String getSkolskaGodina() {
        return skolskaGodina;
    }

    /**
     * Postavlja skolsku godinu evidencije na unetu vrednost.
     * 
     * @param skolskaGodina Nova skolska godina u formatu "xxxx/xxxx".
     */
    public void setSkolskaGodina(String skolskaGodina) {
        this.skolskaGodina = skolskaGodina;
    }

    /**
     * Vraca datum pocetka evidencije.
     * 
     * @return datumPocetka kao Date.
     */
    public Date getDatumPocetka() {
        return datumPocetka;
    }

    /**
     * Postavlja datum pocetka evidencije na unetu vrednost.
     * 
     * @param datumPocetka Novi datum pocetka.
     */
    public void setDatumPocetka(Date datumPocetka) {
        this.datumPocetka = datumPocetka;
    }

    /**
     * Vraca datum zavrsetka evidencije.
     * 
     * @return datumZavrsetka kao Date.
     */
    public Date getDatumZavrsetka() {
        return datumZavrsetka;
    }

    /**
     * Postavlja datum zavrsetka evidencije na unetu vrednost.
     * 
     * @param datumZavrsetka Novi datum zavrsetka.
     */
    public void setDatumZavrsetka(Date datumZavrsetka) {
        this.datumZavrsetka = datumZavrsetka;
    }

    /**
     * Vraca trajanje evidencije.
     * 
     * @return trajanjeEvidencije kao ceo broj.
     */
    public int getTrajanjeEvidencije() {
        return trajanjeEvidencije;
    }

    /**
     * Postavlja trajanje evidencije na unetu vrednost.
     * 
     * @param trajanjeEvidencije Novo trajanje evidencije.
     */
    public void setTrajanjeEvidencije(int trajanjeEvidencije) {
        this.trajanjeEvidencije = trajanjeEvidencije;
    }

    /**
     * Vraca prosecnu ocenu ucenika u okviru evidencije.
     * 
     * @return prosecnaOcena kao decimalni broj.
     */
    public double getProsecnaOcena() {
        return prosecnaOcena;
    }

    /**
     * Postavlja prosecnu ocenu na unetu vrednost.
     * 
     * @param prosecnaOcena Nova prosecna ocena.
     */
    public void setProsecnaOcena(double prosecnaOcena) {
        this.prosecnaOcena = prosecnaOcena;
    }

    /**
     * Vraca broj prisustava ucenika u okviru evidencije.
     * 
     * @return brojPrisustva kao ceo broj.
     */
    public int getBrojPrisustva() {
        return brojPrisustva;
    }

    /**
     * Postavlja broj prisustava na unetu vrednost.
     * 
     * @param brojPrisustva Novi broj prisustava.
     */
    public void setBrojPrisustva(int brojPrisustva) {
        this.brojPrisustva = brojPrisustva;
    }

    /**
     * Vraca profesora koji vodi evidenciju.
     * 
     * @return profesor kao objekat klase Profesor.
     */
    public Profesor getProfesor() {
        return profesor;
    }

    /**
     * Postavlja profesora koji vodi evidenciju na unetu vrednost.
     * 
     * @param profesor Novi profesor.
     */
    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    /**
     * Vraca ucenika na koga se evidencija odnosi.
     * 
     * @return ucenik kao objekat klase Ucenik.
     */
    public Ucenik getUcenik() {
        return ucenik;
    }

    /**
     * Postavlja ucenika na koga se evidencija odnosi na unetu vrednost.
     * 
     * @param ucenik Novi ucenik.
     */
    public void setUcenik(Ucenik ucenik) {
        this.ucenik = ucenik;
    }

    /**
     * Vraca listu stavki evidencije.
     * 
     * @return stavke kao lista objekata klase StavkaEvidencijeCasova.
     */
    public List<StavkaEvidencijeCasova> getStavke() {
        return stavke;
    }

    /**
     * Postavlja listu stavki evidencije na unetu vrednost.
     * 
     * @param stavke Nova lista stavki evidencije.
     */
    public void setStavke(List<StavkaEvidencijeCasova> stavke) {
        this.stavke = stavke;
    }

    /**
     * Vraca String reprezentaciju evidencije casova sa skolskom godinom,
     * ucenikom i profesorom.
     * 
     * @return podaci o evidenciji u formatu
     * "Skolska godina: ####, Ucenik: ####, Profesor: ####".
     */
    @Override
    public String toString() {
        return "Skolska godina: " + skolskaGodina + ", Ucenik: " + ucenik + ", Profesor: " + profesor;
    }

    /**
     * Vraca hash kod evidencije izracunat na osnovu identifikatora.
     * 
     * @return hash kod kao ceo broj.
     */
    @Override
    public int hashCode() {
    	return Objects.hash(idEvidencijaCasova);
    }

    /**
     * Poredi dve evidencije casova po identifikatoru.
     * 
     * @param obj Drugi objekat sa kojim se poredi.
     * @return 
     * <ul>
     * <li><b>true</b> - ako su oba objekta klase EvidencijaCasova sa
     * istim identifikatorom ili ako su na istoj adresi.</li>
     * <li><b>false</b> - ako je drugi objekat null, ako je druge klase
     * ili ako nemaju isti identifikator.</li>
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
        final EvidencijaCasova other = (EvidencijaCasova) obj;
        return Objects.equals(this.idEvidencijaCasova, other.idEvidencijaCasova);
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

    /**
     * Vraca uslov za pretragu evidencija casova.
     * 
     * Ako je postavljen identifikator (veci od nule), pretraga se vrsi
     * samo po identifikatoru. U suprotnom, uslov se gradi na osnovu
     * popunjenih polja: profesora, ucenika i skolske godine.
     * 
     * @return uslov za pretragu kao String ili prazan String ako
     * nijedan kriterijum nije popunjen.
     */
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