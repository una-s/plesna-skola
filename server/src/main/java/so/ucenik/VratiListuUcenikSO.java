/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.ucenik;

import domen.ApstraktniDomenskiObjekat;
import domen.Ucenik;
import repository.Repository;

import java.util.List;
import so.ApstraktnaSO;
/**
 * 
 * @author Una
 */

public class VratiListuUcenikSO extends ApstraktnaSO {

    private List<Ucenik> ucenici;
    
    public VratiListuUcenikSO() {
        super();
    }

    public VratiListuUcenikSO(Repository repository) {
        super(repository);
    }

    public List<Ucenik> getUcenici() {
        return ucenici;
    }

    @Override
    protected void preduslov(ApstraktniDomenskiObjekat ado) throws Exception {
        if (ado == null || !(ado instanceof Ucenik)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Ucenik!");
        }
    }

    @Override
    protected void izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
        Ucenik u = (Ucenik) ado;
        ucenici = repository.getAll(u); 
        
        if(ucenici.isEmpty()){
            System.out.println("Nema ucenika!");
        }
    }
}