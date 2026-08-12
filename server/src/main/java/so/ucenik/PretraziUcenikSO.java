package so.ucenik;

import domen.ApstraktniDomenskiObjekat;
import repository.Repository;
import domen.Ucenik;
import java.util.List;
import so.ApstraktnaSO;

/**
 * Predstavlja sistemsku operaciju kojom se pretrazuju ucenici po zadatim
 * kriterijumima (ime, prezime, plesni nivo).
 * 
 * Nasledjuje ApstraktnaSO i implementira preduslov i izvrsiOperaciju.
 * 
 * @author Una Stankovic
 */
public class PretraziUcenikSO extends ApstraktnaSO {

	/**
	 * Lista ucenika koji odgovaraju zadatim kriterijumima.
	 */
	private List<Ucenik> ucenici;

	/**
	 * Vraca listu ucenika dobijenu izvrsavanjem operacije.
	 * 
	 * @return lista ucenika kao List&lt;Ucenik&gt;.
	 */
	public List<Ucenik> getUcenici() {
		return ucenici;
	}

	/**
	 * Kreira objekat sistemske operacije sa podrazumevanim repozitorijumom.
	 */
	public PretraziUcenikSO() {
		super();
	}

	/**
	 * Kreira objekat sistemske operacije sa unetim repozitorijumom.
	 * 
	 * @param repository Repozitorijum koji sistemska operacija koristi.
	 */
	public PretraziUcenikSO(Repository repository) {
		super(repository);
	}

	/**
	 * Proverava da li je prosledjeni objekat instanca klase Ucenik
	 * i da li je unet bar jedan kriterijum pretrage.
	 * 
	 * @param ado Domenski objekat ciji se preduslovi proveravaju.
	 * @throws java.lang.Exception ako objekat nije instanca klase Ucenik
	 * ili ako nijedan kriterijum pretrage (ime, prezime, plesni nivo)
	 * nije popunjen.
	 */
	@Override
	protected void preduslov(ApstraktniDomenskiObjekat ado) throws Exception {
		if (!(ado instanceof Ucenik)) {
			throw new Exception("Prosledjeni objekat nije instanca klase Ucenik!");
		}
		Ucenik u = (Ucenik) ado;
		boolean imePrazno = u.getIme() == null || u.getIme().isEmpty();
		boolean prezimePrazno = u.getPrezime() == null || u.getPrezime().isEmpty();
		boolean nivoPrazan = u.getPlesniNivo() == null;
		if (imePrazno && prezimePrazno && nivoPrazan) {
			throw new Exception("Barem jedan kriterijum za pretragu mora biti popunjen!");
		}
	}

	/**
	 * Pretrazuje ucenike po zadatim kriterijumima i cuva rezultat
	 * u internoj listi.
	 * 
	 * @param ado Domenski objekat (Ucenik) sa kriterijumima pretrage.
	 * @throws java.lang.Exception ako nijedan ucenik ne odgovara zadatim
	 * kriterijumima.
	 */
	@Override
	protected void izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
		Ucenik u = (Ucenik) ado;
		ucenici = repository.getAll(u);
		if (ucenici.isEmpty()) {
			throw new Exception("Sistem ne moze da nadje ucenike po zadatim kriterijumima!");
		}
	}
}