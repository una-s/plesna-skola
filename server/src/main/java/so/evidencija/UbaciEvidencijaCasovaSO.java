package so.evidencija;

import domen.ApstraktniDomenskiObjekat;
import domen.EvidencijaCasova;
import domen.StavkaEvidencijeCasova;
import java.util.Calendar;
import so.ApstraktnaSO;
import repository.Repository;

/**
 * Predstavlja sistemsku operaciju kojom se dodaje nova evidencija
 * casova zajedno sa njenim stavkama u bazu podataka.
 * 
 * Nasledjuje ApstraktnaSO i implementira preduslov i izvrsiOperaciju.
 * 
 * @author Una Stankovic
 */
public class UbaciEvidencijaCasovaSO extends ApstraktnaSO {

	/**
	 * Kreira objekat sistemske operacije sa podrazumevanim repozitorijumom.
	 */
	public UbaciEvidencijaCasovaSO() {
		super();
	}

	/**
	 * Kreira objekat sistemske operacije sa unetim repozitorijumom.
	 * 
	 * @param repository Repozitorijum koji sistemska operacija koristi.
	 */
	public UbaciEvidencijaCasovaSO(Repository repository) {
		super(repository);
	}

	/**
	 * Proverava ispravnost evidencije casova pre dodavanja.
	 * 
	 * Proverava da evidencija nije null i da je instanca klase
	 * EvidencijaCasova, da je skolska godina uneta i u formatu "xxxx/xxxx",
	 * da su datumi pocetka i zavrsetka uneti i u ispravnom redosledu,
	 * da godine datuma odgovaraju skolskoj godini, da su izabrani profesor
	 * i ucenik, kao i da evidencija ima bar jednu stavku sa ispravnim casom,
	 * ocenom (od 1 do 5) i datumom prisustva u okviru trajanja evidencije.
	 * 
	 * @param ado Domenski objekat ciji se preduslovi proveravaju.
	 * @throws java.lang.Exception ako bilo koji od navedenih uslova
	 * nije ispunjen.
	 */
	@Override
	protected void preduslov(ApstraktniDomenskiObjekat ado) throws Exception {
		if (ado == null) {
			throw new Exception("Evidencija ne sme biti null!");
		}
		if (!(ado instanceof EvidencijaCasova)) {
			throw new Exception("Prosledjeni objekat nije instanca klase EvidencijaCasova!");
		}
		EvidencijaCasova ec = (EvidencijaCasova) ado;
		if (ec.getSkolskaGodina() == null || ec.getSkolskaGodina().isEmpty()) {
			throw new Exception("Skolska godina ne sme biti prazna!");
		}
		if (ec.getDatumPocetka() == null) {
			throw new Exception("Datum pocetka ne sme biti null!");
		}
		if (ec.getDatumZavrsetka() == null) {
			throw new Exception("Datum zavrsetka ne sme biti null!");
		}
		if (ec.getDatumZavrsetka().before(ec.getDatumPocetka())) {
			throw new Exception("Datum zavrsetka mora biti nakon datuma pocetka!");
		}
		// skolskaGodina LIKE '%/%'
		if (!ec.getSkolskaGodina().matches(".*/.+")) {
			throw new Exception("Skolska godina mora biti u formatu 'xxxx/xxxx'!");
		}
		if (ec.getStavke() == null || ec.getStavke().isEmpty()) {
			throw new Exception("Evidencija mora imati bar jednu stavku!");
		}
		if (ec.getProfesor() == null) {
			throw new Exception("Profesor ne sme biti null!");
		}
		if (ec.getUcenik() == null) {
			throw new Exception("Ucenik ne sme biti null!");
		}
		// provera skolske godine i datuma
		String[] godine = ec.getSkolskaGodina().split("/");
		int godinaOd = Integer.parseInt(godine[0]);
		int godinaDo = Integer.parseInt(godine[1]);
		Calendar cal = Calendar.getInstance();
		cal.setTime(ec.getDatumPocetka());
		if (cal.get(Calendar.YEAR) != godinaOd) {
			throw new Exception("Godina datuma pocetka mora biti jednaka prvoj godini skolske godine!");
		}
		cal.setTime(ec.getDatumZavrsetka());
		if (cal.get(Calendar.YEAR) != godinaDo) {
			throw new Exception("Godina datuma zavrsetka mora biti jednaka drugoj godini skolske godine!");
		}
		// provera stavki
		for (StavkaEvidencijeCasova stavka : ec.getStavke()) {
			if (stavka.getCas() == null) {
				throw new Exception("Cas u stavci ne sme biti null!");
			}
			if (stavka.getOcena() < 1 || stavka.getOcena() > 5) {
				throw new Exception("Ocena mora biti izmedju 1 i 5!");
			}
			if (stavka.getDatumPrisustva() == null) {
				throw new Exception("Datum prisustva ne sme biti null!");
			}
			if (stavka.getDatumPrisustva().before(ec.getDatumPocetka())
					|| stavka.getDatumPrisustva().after(ec.getDatumZavrsetka())) {
				throw new Exception("Datum prisustva mora biti izmedju datuma pocetka i zavrsetka evidencije!");
			}
		}
	}

	/**
	 * Dodaje evidenciju casova u bazu, a zatim dodaje svaku njenu stavku
	 * povezanu sa tom evidencijom.
	 * 
	 * @param ado Domenski objekat (EvidencijaCasova) koji se dodaje.
	 * @throws java.lang.Exception ako dodje do greske prilikom dodavanja
	 * evidencije ili njenih stavki.
	 */
	@Override
	protected void izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
		EvidencijaCasova evidencija = (EvidencijaCasova) ado;
		repository.add(evidencija);
		for (StavkaEvidencijeCasova s : evidencija.getStavke()) {
			s.setEvidencijaCasova(evidencija);
			repository.add(s);
		}
	}
}