package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Una
 */
public class StavkaEvidencijeCasova implements ApstraktniDomenskiObjekat {

	private EvidencijaCasova evidencijaCasova;
	private Long rb;
	private int ocena;
	private Date datumPrisustva;
	private String napomena;
	private Cas cas;

	public StavkaEvidencijeCasova() {
	}

	public StavkaEvidencijeCasova(EvidencijaCasova evidencijaCasova, Long rb, int ocena, Date datumPrisustva,
			String napomena, Cas cas) {
		this.evidencijaCasova = evidencijaCasova;
		this.rb = rb;
		this.ocena = ocena;
		this.datumPrisustva = datumPrisustva;
		this.napomena = napomena;
		this.cas = cas;
	}

	public EvidencijaCasova getEvidencijaCasova() {
		return evidencijaCasova;
	}

	public void setEvidencijaCasova(EvidencijaCasova evidencijaCasova) {
		this.evidencijaCasova = evidencijaCasova;
	}

	public Long getRb() {
		return rb;
	}

	public void setRb(Long rb) {
		this.rb = rb;
	}

	public int getOcena() {
		return ocena;
	}

	public void setOcena(int ocena) {
		this.ocena = ocena;
	}

	public Date getDatumPrisustva() {
		return datumPrisustva;
	}

	public void setDatumPrisustva(Date datumPrisustva) {
		this.datumPrisustva = datumPrisustva;
	}

	public String getNapomena() {
		return napomena;
	}

	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}

	public Cas getCas() {
		return cas;
	}

	public void setCas(Cas cas) {
		this.cas = cas;
	}

	@Override
	public String toString() {
		return rb + " " + ocena;
	}

	@Override
	public int hashCode() {
		return Objects.hash(rb, evidencijaCasova);
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
