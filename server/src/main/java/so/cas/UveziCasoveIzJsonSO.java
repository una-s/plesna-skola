package so.cas;

import com.google.gson.Gson;
import domen.ApstraktniDomenskiObjekat;
import domen.Cas;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import so.ApstraktnaSO;
import repository.Repository;

/**
 * Predstavlja sistemsku operaciju kojom se casovi ucitavaju iz JSON fajla.
 * 
 * Nasledjuje ApstraktnaSO i implementira preduslov i izvrsiOperaciju.
 * Casovi se citaju iz JSON fajla na zadatoj putanji i cuvaju u internoj
 * listi, odakle mogu biti preuzeti getter metodom.
 * 
 * @author Una Stankovic
 */
public class UveziCasoveIzJsonSO extends ApstraktnaSO {

	/**
	 * Putanja do JSON fajla iz koga se ucitavaju casovi.
	 */
	private String putanja;

	/**
	 * Lista casova ucitana iz JSON fajla.
	 */
	private List<Cas> casovi;

	/**
	 * Kreira objekat sistemske operacije sa podrazumevanim repozitorijumom
	 * i zadatom putanjom do JSON fajla.
	 * 
	 * @param putanja Putanja do JSON fajla iz koga se ucitavaju casovi.
	 */
	public UveziCasoveIzJsonSO(String putanja) {
		super();
		this.putanja = putanja;
	}

	/**
	 * Kreira objekat sistemske operacije sa unetim repozitorijumom
	 * i zadatom putanjom do JSON fajla.
	 * 
	 * @param repository Repozitorijum koji sistemska operacija koristi.
	 * @param putanja Putanja do JSON fajla iz koga se ucitavaju casovi.
	 */
	public UveziCasoveIzJsonSO(Repository repository, String putanja) {
		super(repository);
		this.putanja = putanja;
	}

	/**
	 * Proverava da li je prosledjeni objekat instanca klase Cas
	 * i da li je zadata putanja do JSON fajla.
	 * 
	 * @param ado Domenski objekat ciji se preduslovi proveravaju.
	 * @throws java.lang.Exception ako objekat nije instanca klase Cas
	 * ili ako putanja do fajla nije zadata.
	 */
	@Override
	protected void preduslov(ApstraktniDomenskiObjekat ado) throws Exception {
		if (!(ado instanceof Cas)) {
			throw new Exception("Prosledjeni objekat nije instanca klase Cas!");
		}
		if (putanja == null || putanja.isEmpty()) {
			throw new Exception("Putanja do JSON fajla ne sme biti prazna!");
		}
	}

	/**
	 * Ucitava casove iz JSON fajla na zadatoj putanji i cuva ih u
	 * internoj listi.
	 * 
	 * @param ado Domenski objekat (Cas) na osnovu koga se izvrsava operacija.
	 * @throws java.lang.Exception ako dodje do greske prilikom citanja
	 * JSON fajla.
	 */
	@Override
	protected void izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
		Gson gson = new Gson();
		try (FileReader reader = new FileReader(putanja)) {
			Cas[] niz = gson.fromJson(reader, Cas[].class);
			casovi = Arrays.asList(niz);
		}
	}

	/**
	 * Vraca listu casova ucitanu iz JSON fajla.
	 * 
	 * @return lista casova kao List&lt;Cas&gt;.
	 */
	public List<Cas> getCasovi() {
		return casovi;
	}
}