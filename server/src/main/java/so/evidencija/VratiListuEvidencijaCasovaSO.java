/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.evidencija;

import domen.ApstraktniDomenskiObjekat;
import domen.EvidencijaCasova;
import java.util.List;
import so.ApstraktnaSO;
/**
 * 
 * @author Una
 */

public class VratiListuEvidencijaCasovaSO extends ApstraktnaSO {

    private List<EvidencijaCasova> evidencije;

    public List<EvidencijaCasova> getEvidencije() {
        return evidencije;
    }

    @Override
    protected void preduslov(ApstraktniDomenskiObjekat ado) throws Exception {
        if (ado == null || !(ado instanceof EvidencijaCasova)) {
            throw new Exception("Prosledjeni objekat nije instanca klase EvidencijeCasova!");
        }
    }

    @Override
    protected void izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
        EvidencijaCasova ev = (EvidencijaCasova) ado;
        evidencije = repository.getAll(ev);
        
        if(evidencije.isEmpty()){
            System.out.println("Nema evidencije casova!");
        }
    }
}