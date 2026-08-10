package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja jednu stavku evidencije casova - pojedinacan cas koji je
 * ucenik pohadjao u okviru jedne evidencije.
 * 
 * Svaka stavka sadrzi redni broj, ocenu, datum prisustva, napomenu, cas
 * na koji se odnosi i evidenciju kojoj pripada. Koristi kompozitni
 * primarni kljuc (rb, evidencija_casova).
 * 
 * Implementira ApstraktniDomenskiObjekat za rad sa tabelom
 * "stavka_evidencije" u bazi podataka.
 * 
 * @author Una Stankovic
 */
public class StavkaEvidencijeCasova implements ApstraktniDomenskiObjekat {

	/**
	 * Evidencija casova kojoj stavka pripada.
	 */
	private EvidencijaCasova evidencijaCasova;

	/**
	 * Redni broj stavke u okviru evidencije kao Long.
	 */
	private Long rb;

	/**
	 * Ocena ucenika na casu kao ceo broj.
	 */
	private int ocena;

	/**
	 * Datum prisustva ucenika na casu.
	 */
	private Date datumPrisustva;

	/**
	 * Napomena uz stavku evidencije kao String.
	 */
	private String napomena;

	/**
	 * Cas na koji se stavka odnosi.
	 */
	private Cas cas;

	/**
	 * Kreira objekat klase StavkaEvidencijeCasova sa podrazumevanim (null)
	 * vrednostima.
	 */
	public StavkaEvidencijeCasova() {
	}

	/**
	 * Kreira objekat klase StavkaEvidencijeCasova sa zadatim vrednostima.
	 * 
	 * @param evidencijaCasova Evidencija kojoj stavka pripada.
	 * @param rb Redni broj stavke.
	 * @param ocena Ocena ucenika na casu.
	 * @param datumPrisustva Datum prisustva na casu.
	 * @param napomena Napomena uz stavku.
	 * @param cas Cas na koji se stavka odnosi.
	 */
	public StavkaEvidencijeCasova(EvidencijaCasova evidencijaCasova, Long rb, int ocena, Date datumPrisustva,
			String napomena, Cas cas) {
		this.evidencijaCasova = evidencijaCasova;
		this.rb = rb;
		this.ocena = ocena;
		this.datumPrisustva = datumPrisustva;
		this.napomena = napomena;
		this.cas = cas;
	}

	/**
	 * Vraca evidenciju kojoj stavka pripada.
	 * 
	 * @return evidencijaCasova kao objekat klase EvidencijaCasova.
	 */
	public EvidencijaCasova getEvidencijaCasova() {
		return evidencijaCasova;
	}

	/**
	 * Postavlja evidenciju kojoj stavka pripada na unetu vrednost.
	 * 
	 * @param evidencijaCasova Nova evidencija kojoj stavka pripada.
	 */
	public void setEvidencijaCasova(EvidencijaCasova evidencijaCasova) {
		this.evidencijaCasova = evidencijaCasova;
	}

	/**
	 * Vraca redni broj stavke.
	 * 
	 * @return rb kao Long.
	 */
	public Long getRb() {
		return rb;
	}

	/**
	 * Postavlja redni broj stavke na unetu vrednost.
	 * 
	 * @param rb Novi redni broj stavke.
	 */
	public void setRb(Long rb) {
		this.rb = rb;
	}

	/**
	 * Vraca ocenu ucenika na casu.
	 * 
	 * @return ocena kao ceo broj.
	 */
	public int getOcena() {
		return ocena;
	}

	/**
	 * Postavlja ocenu na unetu vrednost.
	 * 
	 * @param ocena Nova ocena ucenika.
	 */
	public void setOcena(int ocena) {
		this.ocena = ocena;
	}

	/**
	 * Vraca datum prisustva ucenika na casu.
	 * 
	 * @return datumPrisustva kao Date.
	 */
	public Date getDatumPrisustva() {
		return datumPrisustva;
	}

	/**
	 * Postavlja datum prisustva na unetu vrednost.
	 * 
	 * @param datumPrisustva Novi datum prisustva.
	 */
	public void setDatumPrisustva(Date datumPrisustva) {
		this.datumPrisustva = datumPrisustva;
	}

	/**
	 * Vraca napomenu uz stavku.
	 * 
	 * @return napomena kao String.
	 */
	public String getNapomena() {
		return napomena;
	}

	/**
	 * Postavlja napomenu uz stavku na unetu vrednost.
	 * 
	 * @param napomena Nova napomena.
	 */
	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}

	/**
	 * Vraca cas na koji se stavka odnosi.
	 * 
	 * @return cas kao objekat klase Cas.
	 */
	public Cas getCas() {
		return cas;
	}

	/**
	 * Postavlja cas na koji se stavka odnosi na unetu vrednost.
	 * 
	 * @param cas Novi cas.
	 */
	public void setCas(Cas cas) {
		this.cas = cas;
	}

	/**
	 * Vraca String reprezentaciju stavke (redni broj i ocena).
	 * 
	 * @return podaci o stavci u formatu "rb ocena".
	 */
	@Override
	public String toString() {
		return rb + " " + ocena;
	}

	/**
	 * Vraca hash kod stavke izracunat na osnovu rednog broja i evidencije.
	 * 
	 * @return hash kod kao ceo broj.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(rb, evidencijaCasova);
	}

	/**
	 * Poredi dve stavke po rednom broju i evidenciji kojoj pripadaju.
	 * 
	 * @param obj Drugi objekat sa kojim se poredi.
	 * @return 
	 * <ul>
	 * <li><b>true</b> - ako su oba objekta klase StavkaEvidencijeCasova sa
	 * istim rednim brojem i istom evidencijom ili ako su na istoj adresi.</li>
	 * <li><b>false</b> - ako je drugi objekat null, ako je druge klase
	 * ili ako nemaju isti redni broj i evidenciju.</li>
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
		final StavkaEvidencijeCasova other = (StavkaEvidencijeCasova) obj;
		if (!Objects.equals(this.rb, other.rb)) {
			return false;
		}
		return Objects.equals(this.evidencijaCasova, other.evidencijaCasova);
	}

	@Override
	public String vratiNazivTabele() {
		return "stavka_evidencije";
	}

	@Override
	public String alias() {
		return " se ";
	}

	@Override
	public String join() {
		return " JOIN cas c ON se.cas = c.idCas ";
	}

	/**
	 * Vraca uslov za pretragu stavki evidencije.
	 * 
	 * Ako je postavljena evidencija, uslov se gradi tako da se vrate
	 * sve stavke koje pripadaju toj evidenciji.
	 * 
	 * @return uslov za pretragu kao String ili prazan String ako
	 * evidencija nije postavljena.
	 */
	@Override
	public String uslovZaSelect() {
		if (evidencijaCasova != null) {
			return "se.evidencija_casova = " + evidencijaCasova.getIdEvidencijaCasova();
		}
		return "";
	}

	@Override
	public String vratiKoloneZaUbacivanje() {
		return "datumPrisustva, ocena, napomena, evidencija_casova, cas";
	}

	@Override
	public String vratiVrednostZaUbacivanje() {
		return "'" + new java.sql.Date(datumPrisustva.getTime()) + "', " + ocena + ", '" + napomena + "', "
				+ evidencijaCasova.getIdEvidencijaCasova() + ", " + cas.getIdCas();
	}

// Proveri i vratiVrednostZaIzmenu ako je imaš
	@Override
	public String vratiVrednostZaIzmenu() {
		return "datumPrisustva = '" + new java.sql.Date(datumPrisustva.getTime()) + "', " + "ocena = " + ocena + ", "
				+ "napomena = '" + napomena + "', " + "cas = " + cas.getIdCas();
	}

	@Override
	public String vratiPrimarniKljuc() {
		return "rb = " + rb + " AND evidencija_casova = " + evidencijaCasova.getIdEvidencijaCasova();
	}

	@Override
	public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
		List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
		while (rs.next()) {
			Long rb = rs.getLong("se.rb");
			int ocena = rs.getInt("se.ocena");
			Date datumPrisustva = rs.getDate("se.datumPrisustva");
			String napomena = rs.getString("se.napomena");

			Long idCas = rs.getLong("c.idCas");
			String nazivCas = rs.getString("c.naziv");
			int trajanje = rs.getInt("c.trajanje");
			String stilPlesa = rs.getString("c.stilPlesa");

			Cas c = new Cas(idCas, nazivCas, trajanje, stilPlesa);

			EvidencijaCasova ec = new EvidencijaCasova();
			ec.setIdEvidencijaCasova(rs.getLong("se.evidencija_casova"));

			StavkaEvidencijeCasova se = new StavkaEvidencijeCasova();
			se.setRb(rb);
			se.setEvidencijaCasova(ec);
			se.setOcena(ocena);
			se.setDatumPrisustva(datumPrisustva);
			se.setNapomena(napomena);
			se.setCas(c);

			lista.add(se);
		}
		return lista;
	}

	@Override
	public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
		if (rs.next()) {
			Long rb = rs.getLong("se.rb");
			int ocena = rs.getInt("se.ocena");
			Date datumPrisustva = rs.getDate("se.datumPrisustva");
			String napomena = rs.getString("se.napomena");

			Long idCas = rs.getLong("c.idCas");
			String nazivCas = rs.getString("c.naziv");
			int trajanje = rs.getInt("c.trajanje");
			String stilPlesa = rs.getString("c.stilPlesa");

			Cas c = new Cas(idCas, nazivCas, trajanje, stilPlesa);

			EvidencijaCasova ec = new EvidencijaCasova();
			ec.setIdEvidencijaCasova(rs.getLong("se.evidencija_casova"));

			StavkaEvidencijeCasova se = new StavkaEvidencijeCasova();
			se.setRb(rb);
			se.setEvidencijaCasova(ec);
			se.setOcena(ocena);
			se.setDatumPrisustva(datumPrisustva);
			se.setNapomena(napomena);
			se.setCas(c);

			return se;
		}
		return null;
	}

	@Override
	public void postaviId(Long id) {
		this.rb = id;
	}
}