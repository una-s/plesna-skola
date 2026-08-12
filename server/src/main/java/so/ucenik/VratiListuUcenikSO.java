package so.ucenik;

import domen.ApstraktniDomenskiObjekat;
import domen.Ucenik;
import repository.Repository;
import java.util.List;
import so.ApstraktnaSO;

/**
 * Predstavlja sistemsku operaciju kojom se vraca lista svih ucenika
 * iz sistema.
 * 
 * Nasledjuje ApstraktnaSO i implementira preduslov i izvrsiOperaciju.
 * 
 * @author Una Stankovic
 */
public class VratiListuUcenikSO extends ApstraktnaSO {

    /**
     * Lista ucenika vracena iz baze podataka.
     */
    private List<Ucenik> ucenici;

    /**
     * Kreira objekat sistemske operacije sa podrazumevanim repozitorijumom.
     */
    public VratiListuUcenikSO() {
        super();
    }

    /**
     * Kreira objekat sistemske operacije sa unetim repozitorijumom.
     * 
     * @param repository Repozitorijum koji sistemska operacija koristi.
     */
    public VratiListuUcenikSO(Repository repository) {
        super(repository);
    }

    /**
     * Vraca listu ucenika dobijenu izvrsavanjem operacije.
     * 
     * @return lista ucenika kao List&lt;Ucenik&gt;.
     */
    public List<Ucenik> getUcenici() {
        return ucenici;
    }

    /**
     * Proverava da li je prosledjeni objekat instanca klase Ucenik.
     * 
     * @param ado Domenski objekat ciji se preduslovi proveravaju.
     * @throws java.lang.Exception ako objekat nije instanca klase Ucenik.
     */
    @Override
    protected void preduslov(ApstraktniDomenskiObjekat ado) throws Exception {
        if (ado == null || !(ado instanceof Ucenik)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Ucenik!");
        }
    }

    /**
     * Vraca listu svih ucenika iz baze i cuva je u internoj listi.
     * 
     * @param ado Domenski objekat (Ucenik) na osnovu koga se vraca lista.
     * @throws java.lang.Exception ako dodje do greske prilikom vracanja
     * ucenika iz baze.
     */
    @Override
    protected void izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
        Ucenik u = (Ucenik) ado;
        ucenici = repository.getAll(u); 
        
        if(ucenici.isEmpty()){
            System.out.println("Nema ucenika!");
        }
    }
}