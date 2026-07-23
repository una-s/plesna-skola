/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domen.ApstraktniDomenskiObjekat;
import repository.Repository;
import repository.db.DbRepository;
import repository.db.impl.DbRepositoryGeneric;

/**
 *
 * @author Una
 */
public abstract class ApstraktnaSO {
    protected final Repository repository;

    public ApstraktnaSO() {
        this.repository = new DbRepositoryGeneric();
    }

    public void izvrsi(ApstraktniDomenskiObjekat ado) throws Exception {
        try {
            preduslov(ado);
            zapocniTransakciju();
            izvrsiOperaciju(ado);
            potvrdiTransakciju();
            System.out.println("Uspesno izvrsena operacija!");
        } catch (Exception ex) {
            System.out.println("Neuspesno izvrsena operacija!");
            ponistiTransakciju();
            throw ex;
        } finally {
            ((DbRepository) repository).disconnect();
        }
    }

    protected abstract void preduslov(ApstraktniDomenskiObjekat ado) throws Exception;

    protected abstract void izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception;

    private void zapocniTransakciju() throws Exception {
        ((DbRepository) repository).connect();
    }

    protected void potvrdiTransakciju() throws Exception {
        ((DbRepository) repository).commit();
    }

    protected void ponistiTransakciju() throws Exception {
        ((DbRepository) repository).rollback();
    }
}
