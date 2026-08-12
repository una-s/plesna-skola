package so.ucenik;

import domen.ApstraktniDomenskiObjekat;
import repository.Repository;
import domen.PlesniNivo;
import domen.Ucenik;
import so.ApstraktnaSO;

/**
 * Predstavlja sistemsku operaciju kojom se dodaje novi ucenik
 * u bazu podataka.
 * 
 * Nasledjuje ApstraktnaSO i implementira preduslov i izvrsiOperaciju.
 * 
 * @author Una Stankovic
 */
public class UbaciUcenikSO extends ApstraktnaSO {

	/**
	 * Kreira objekat sistemske operacije sa podrazumevanim repozitorijumom.
	 */
	public UbaciUcenikSO() {
		super();
	}

	/**
	 * Kreira objekat sistemske operacije sa unetim repozitorijumom.
	 * 
	 * @param repository Repozitorijum koji sistemska operacija koristi.
	 */
	public UbaciUcenikSO(Repository repository) {
		super(repository);
	}

	/**
	 * Proverava ispravnost ucenika pre dodavanja.
	 * 
	 * Proverava da ucenik nije null i da je instanca klase Ucenik,
	 * kao i da su uneti ime, prezime, broj telefona i plesni nivo.
	 * 
	 * @param ado Domenski objekat ciji se preduslovi proveravaju.
	 * @throws java.lang.Exception ako je ucenik null, ako nije instanca
	 * klase Ucenik, ili ako su ime, prezime, broj telefona prazni odnosno
	 * plesni nivo nije izabran.
	 */
	@Override
	protected void preduslov(ApstraktniDomenskiObjekat ado) throws Exception {
		if (ado == null) {
			throw new Exception("Ucenik ne sme biti null!");
		}
		if (!(ado instanceof Ucenik)) {
			throw new Exception("Prosledjeni objekat nije instanca klase Ucenik!");
		}
		Ucenik u = (Ucenik) ado;
		if (u.getIme() == null || u.getIme().isEmpty()) {
			throw new Exception("Ime ne sme biti prazno!");
		}
		if (u.getPrezime() == null || u.getPrezime().isEmpty()) {
			throw new Exception("Prezime ne sme biti prazno!");
		}
		if (u.getBrojTelefona() == null || u.getBrojTelefona().isEmpty()) {
			throw new Exception("Broj telefona ne sme biti prazan!");
		}
		if (u.getPlesniNivo() == null) {
			throw new Exception("Plesni nivo ne sme biti prazan!");
		}
	}

	/**
	 * Dodaje ucenika u bazu podataka.
	 * 
	 * @param ado Domenski objekat (Ucenik) koji se dodaje.
	 * @throws java.lang.Exception ako dodje do greske prilikom dodavanja
	 * ucenika.
	 */
	@Override
	protected void izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
		Ucenik u = (Ucenik) ado;
		repository.add(u);
	}
}