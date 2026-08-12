package so.evidencija;

import domen.ApstraktniDomenskiObjekat;
import repository.Repository;
import domen.EvidencijaCasova;
import domen.StavkaEvidencijeCasova;
import java.util.List;
import so.ApstraktnaSO;

/**
 * Predstavlja sistemsku operaciju kojom se vraca jedna evidencija casova
 * zajedno sa svojim stavkama na osnovu identifikatora.
 * 
 * Nasledjuje ApstraktnaSO i implementira preduslov i izvrsiOperaciju.
 * 
 * @author Una Stankovic
 */
public class VratiEvidencijaCasovaSO extends ApstraktnaSO {

	/**
	 * Evidencija casova vracena iz baze podataka.
	 */
	private EvidencijaCasova evidencija;

	/**
	 * Vraca evidenciju dobijenu izvrsavanjem operacije.
	 * 
	 * @return evidencija kao objekat klase EvidencijaCasova.
	 */
	public EvidencijaCasova getEvidencija() {
		return evidencija;
	}

	/**
	 * Kreira objekat sistemske operacije sa podrazumevanim repozitorijumom.
	 */
	public VratiEvidencijaCasovaSO() {
		super();
	}

	/**
	 * Kreira objekat sistemske operacije sa unetim repozitorijumom.
	 * 
	 * @param repository Repozitorijum koji sistemska operacija koristi.
	 */
	public VratiEvidencijaCasovaSO(Repository repository) {
		super(repository);
	}

	/**
	 * Proverava da li je prosledjeni objekat instanca klase EvidencijaCasova
	 * i da li ima validan identifikator.
	 * 
	 * @param ado Domenski objekat ciji se preduslovi proveravaju.
	 * @throws java.lang.Exception ako objekat nije instanca klase
	 * EvidencijaCasova ili ako nema validan identifikator (null ili
	 * manji odnosno jednak nuli).
	 */
	@Override
	protected void preduslov(ApstraktniDomenskiObjekat ado) throws Exception {
		if (!(ado instanceof EvidencijaCasova)) {
			throw new Exception("Prosledjeni objekat nije instanca klase EvidencijaCasova!");
		}
		EvidencijaCasova ev = (EvidencijaCasova) ado;
		if (ev.getIdEvidencijaCasova() == null || ev.getIdEvidencijaCasova() <= 0) {
			throw new Exception("EvidencijaCasova mora imati validan ID!");
		}
	}

	/**
	 * Vraca evidenciju casova na osnovu identifikatora, a zatim ucitava
	 * i postavlja njene stavke.
	 * 
	 * @param ado Domenski objekat (EvidencijaCasova) sa identifikatorom.
	 * @throws java.lang.Exception ako evidencija sa zadatim identifikatorom
	 * ne postoji.
	 */
	@Override
	protected void izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
		evidencija = (EvidencijaCasova) repository.get(ado);
		if (evidencija == null) {
			throw new Exception("Sistem ne moze da nadje evidenciju casova!");
		}
		StavkaEvidencijeCasova pomocna = new StavkaEvidencijeCasova();
		pomocna.setEvidencijaCasova(evidencija);
		List<StavkaEvidencijeCasova> stavke = (List<StavkaEvidencijeCasova>) repository.getAll(pomocna);
		evidencija.setStavke(stavke);
	}
}