package so.evidencija;

import domen.ApstraktniDomenskiObjekat;
import domen.EvidencijaCasova;
import java.util.List;
import so.ApstraktnaSO;
import repository.Repository;

/**
 * Predstavlja sistemsku operaciju kojom se pretrazuju evidencije casova
 * po zadatim kriterijumima (profesor, ucenik, skolska godina).
 * 
 * Nasledjuje ApstraktnaSO i implementira preduslov i izvrsiOperaciju.
 * 
 * @author Una Stankovic
 */
public class PretraziEvidencijaCasovaSO extends ApstraktnaSO {

	/**
	 * Lista evidencija casova koje odgovaraju zadatim kriterijumima.
	 */
	private List<EvidencijaCasova> evidencije;

	/**
	 * Vraca listu evidencija dobijenu izvrsavanjem operacije.
	 * 
	 * @return lista evidencija kao List&lt;EvidencijaCasova&gt;.
	 */
	public List<EvidencijaCasova> getEvidencije() {
		return evidencije;
	}

	/**
	 * Kreira objekat sistemske operacije sa podrazumevanim repozitorijumom.
	 */
	public PretraziEvidencijaCasovaSO() {
		super();
	}

	/**
	 * Kreira objekat sistemske operacije sa unetim repozitorijumom.
	 * 
	 * @param repository Repozitorijum koji sistemska operacija koristi.
	 */
	public PretraziEvidencijaCasovaSO(Repository repository) {
		super(repository);
	}

	/**
	 * Proverava da li je prosledjeni objekat instanca klase EvidencijaCasova
	 * i da li je unet bar jedan kriterijum pretrage.
	 * 
	 * @param ado Domenski objekat ciji se preduslovi proveravaju.
	 * @throws java.lang.Exception ako objekat nije instanca klase
	 * EvidencijaCasova ili ako nije unet nijedan kriterijum pretrage
	 * (profesor, ucenik i skolska godina su prazni).
	 */
	@Override
	protected void preduslov(ApstraktniDomenskiObjekat ado) throws Exception {
		if (!(ado instanceof EvidencijaCasova)) {
			throw new Exception("Prosledjeni objekat nije instanca klase EvidencijaCasova!");
		}
		EvidencijaCasova ev = (EvidencijaCasova) ado;
		if (ev.getProfesor() == null && ev.getUcenik() == null
				&& (ev.getSkolskaGodina() == null || ev.getSkolskaGodina().isEmpty())) {
			throw new Exception("Morate uneti bar jedan kriterijum pretrage!");
		}
	}

	/**
	 * Pretrazuje evidencije casova po zadatim kriterijumima i cuva
	 * rezultat u internoj listi.
	 * 
	 * @param ado Domenski objekat (EvidencijaCasova) sa kriterijumima pretrage.
	 * @throws java.lang.Exception ako nijedna evidencija ne odgovara
	 * zadatim kriterijumima.
	 */
	@Override
	protected void izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
		EvidencijaCasova ev = (EvidencijaCasova) ado;
		evidencije = repository.getAll(ev);
		if (evidencije.isEmpty()) {
			throw new Exception("Sistem ne moze da nadje evidencije casova po zadatim kriterijumima!");
		}
	}
}