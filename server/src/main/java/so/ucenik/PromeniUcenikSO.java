package so.ucenik;

import domen.ApstraktniDomenskiObjekat;
import domen.Ucenik;
import so.ApstraktnaSO;
import repository.Repository;

/**
 * Predstavlja sistemsku operaciju kojom se menjaju podaci postojeceg
 * ucenika u bazi podataka.
 * 
 * Nasledjuje ApstraktnaSO i implementira preduslov i izvrsiOperaciju.
 * 
 * @author Una Stankovic
 */
public class PromeniUcenikSO extends ApstraktnaSO {

	/**
	 * Kreira objekat sistemske operacije sa podrazumevanim repozitorijumom.
	 */
	public PromeniUcenikSO() {
		super();
	}

	/**
	 * Kreira objekat sistemske operacije sa unetim repozitorijumom.
	 * 
	 * @param repository Repozitorijum koji sistemska operacija koristi.
	 */
	public PromeniUcenikSO(Repository repository) {
		super(repository);
	}

	/**
	 * Proverava ispravnost ucenika pre izmene.
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
	 * Menja podatke ucenika u bazi podataka.
	 * 
	 * @param ado Domenski objekat (Ucenik) koji se menja.
	 * @throws java.lang.Exception ako dodje do greske prilikom izmene
	 * ucenika.
	 */
	@Override
	protected void izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
		Ucenik u = (Ucenik) ado;
		repository.edit(u);
	}
}