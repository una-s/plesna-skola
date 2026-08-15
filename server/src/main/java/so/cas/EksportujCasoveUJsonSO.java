package so.cas;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domen.ApstraktniDomenskiObjekat;
import domen.Cas;
import java.io.FileWriter;
import java.util.List;
import so.ApstraktnaSO;
import repository.Repository;

/**
 * Predstavlja sistemsku operaciju kojom se svi casovi iz baze izvoze
 * u JSON fajl.
 * 
 * Nasledjuje ApstraktnaSO i implementira preduslov i izvrsiOperaciju.
 * Casovi se prvo ucitavaju iz baze, a zatim upisuju u JSON fajl na
 * zadatoj putanji, u formatiranom (ne compact) obliku.
 * 
 * @author Una Stankovic
 */
public class EksportujCasoveUJsonSO extends ApstraktnaSO {

	/**
	 * Putanja do JSON fajla u koji se izvoze casovi.
	 */
	private String putanja;

	/**
	 * Kreira objekat sistemske operacije sa podrazumevanim repozitorijumom
	 * i zadatom putanjom do JSON fajla.
	 * 
	 * @param putanja Putanja do JSON fajla u koji se izvoze casovi.
	 */
	public EksportujCasoveUJsonSO(String putanja) {
		super();
		this.putanja = putanja;
	}

	/**
	 * Kreira objekat sistemske operacije sa unetim repozitorijumom
	 * i zadatom putanjom do JSON fajla.
	 * 
	 * @param repository Repozitorijum koji sistemska operacija koristi.
	 * @param putanja Putanja do JSON fajla u koji se izvoze casovi.
	 */
	public EksportujCasoveUJsonSO(Repository repository, String putanja) {
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
	 * Ucitava sve casove iz baze i upisuje ih u JSON fajl na zadatoj
	 * putanji, u formatiranom (ne compact) obliku.
	 * 
	 * @param ado Domenski objekat (Cas) na osnovu koga se vracaju casovi.
	 * @throws java.lang.Exception ako dodje do greske prilikom ucitavanja
	 * casova iz baze ili prilikom upisa u JSON fajl.
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected void izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
		Cas c = (Cas) ado;
		List<Cas> casovi = (List<Cas>) (List<?>) repository.getAll(c);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(casovi);

		try (FileWriter writer = new FileWriter(putanja)) {
			writer.write(json);
		}
	}

	/**
	 * Vraca putanju do JSON fajla u koji se izvoze casovi.
	 * 
	 * @return putanja kao String.
	 */
	public String getPutanja() {
		return putanja;
	}
}