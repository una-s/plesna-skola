/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.util.List;
import java.sql.ResultSet;

/**
 * Predstavlja apstraktni domenski objekat koji mogu da implementiraju sve
 * domenske klase u sistemu.
 * 
 * Definise metode potrebne za rad sa bazom podataka - vracanje naziva tabele,
 * kolona i vrednosti za ubacivanje i izmenu, uslova za pretragu, kao i za
 * kreiranje domenskih objekata iz rezultata upita (ResultSet).
 * 
 * @author Una Stankovic
 * @version 1.0
 */
public interface ApstraktniDomenskiObjekat extends Serializable {

	/**
	 * Vraca naziv tabele u bazi podataka koja odgovara domenskom objektu.
	 * 
	 * @return Naziv tabele kao String.
	 */
	public String vratiNazivTabele();

	
	/**
     * Kreira i vraca listu domenskih objekata na osnovu rezultata
     * upita nad bazom podataka.
     * 
     * @param rs Rezultat upita (ResultSet) iz koga se citaju podaci.
     * @return Lista domenskih objekata kreiranih iz rezultata upita.
     * @throws java.lang.Exception ako dodje do greske pri citanju
     * iz rezultata upita.
     */
	public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception;

	
	/**
     * Vraca nazive kolona u koje se ubacuju vrednosti prilikom
     * dodavanja novog sloga u bazu.
     * 
     * @return Nazivi kolona za ubacivanje, razdvojeni zarezom, kao String.
     */
	public String vratiKoloneZaUbacivanje();

	
	 /**
     * Vraca vrednosti atributa domenskog objekta formatirane za
     * ubacivanje novog sloga u bazu.
     * 
     * @return Vrednosti za ubacivanje kao String.
     */
	public String vratiVrednostZaUbacivanje();

	
	/**
     * Vraca uslov koji jedinstveno identifikuje slog u bazi na osnovu
     * primarnog kljuca domenskog objekta.
     * 
     * @return Uslov sa primarnim kljucem kao String.
     */
	public String vratiPrimarniKljuc();

	
	 /**
     * Kreira i vraca jedan domenski objekat na osnovu rezultata
     * upita nad bazom podataka.
     * 
     * @param rs Rezultat upita (ResultSet) iz koga se citaju podaci.
     * @return Domenski objekat kreiran iz rezultata upita ili null
     * ako u rezultatu nema sloga.
     * @throws java.lang.Exception ako dodje do greske pri citanju
     * iz rezultata upita.
     */
	public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception;

	
	/**
     * Vraca parove kolona i vrednosti formatirane za izmenu
     * postojeceg sloga u bazi.
     * 
     * @return Vrednosti za izmenu kao String.
     */
	public String vratiVrednostZaIzmenu();

	
	/**
     * Vraca deo SQL upita za spajanje (JOIN) sa drugim tabelama,
     * ako je potrebno.
     * 
     * @return JOIN deo upita kao String ili prazan String ako
     * spajanje nije potrebno.
     */
	public String join();

	
	/**
     * Vraca uslov za pretragu (WHERE deo upita) na osnovu popunjenih
     * atributa domenskog objekta.
     * 
     * @return Uslov za pretragu kao String ili prazan String ako
     * nijedan kriterijum nije popunjen.
     */
	public String uslovZaSelect();

	
	 /**
     * Vraca alijas (skracenicu) tabele koji se koristi u SQL upitima.
     * 
     * @return Alijas tabele kao String.
     */
	public String alias();

	
	/**
     * Postavlja vrednost primarnog kljuca (ID) domenskog objekta.
     * 
     * @param id Nova vrednost primarnog kljuca kao Long.
     */
	void postaviId(Long id);
}
