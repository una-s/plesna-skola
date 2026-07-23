/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import domen.Profesor;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Una
 */
public class Komunikacija {

    private static Komunikacija instance;
    private Socket s;
    private Posiljalac posiljalac;
    private Primalac primalac;

    private Komunikacija() throws IOException {
        this.s = new Socket("localhost", 9000);
        this.posiljalac = new Posiljalac(s);
        this.primalac = new Primalac(s);
    }

    public static Komunikacija getInstance() throws IOException {
        if (instance == null) {
            instance = new Komunikacija();
        }
        return instance;
    }

    public Odgovor login(Zahtev zahtev) throws Exception {
        posiljalac.posalji(zahtev);
        System.out.println("Poslat zahtev za login...");
        return (Odgovor) primalac.primi();
    }

    public Odgovor vratiUcenike(Zahtev zahtev) throws Exception {
        posiljalac.posalji(zahtev);
        System.out.println("Poslat zahtev za vracanje svih ucenika...");
        return (Odgovor) primalac.primi();
    }

    public Odgovor vratiPlesneNivoe(Zahtev zahtev) throws Exception {
        posiljalac.posalji(zahtev);
        System.out.println("Poslat zahtev za vracanje svih plesnih nivoa...");
        return (Odgovor) primalac.primi();
    }

    public Odgovor kreirajUcenika(Zahtev zahtev) throws Exception {
        posiljalac.posalji(zahtev);
        System.out.println("Poslat zahtev za kreiranje novog ucenika...");
        return (Odgovor) primalac.primi();
    }

    public Odgovor promeniUcenika(Zahtev zahtev) throws Exception {
        posiljalac.posalji(zahtev);
        System.out.println("Poslat zahtev za promenu ucenika...");
        return (Odgovor) primalac.primi();
    }

    public Odgovor obrisiUcenika(Zahtev zahtev) throws Exception {
        posiljalac.posalji(zahtev);
        System.out.println("Poslat zahtev za brisanje ucenika...");
        return (Odgovor) primalac.primi();
    }

    public Odgovor pretraziUcenike(Zahtev zahtev) throws Exception {
        posiljalac.posalji(zahtev);
        System.out.println("Poslat zahtev za pretrazivanje ucenika...");
        return (Odgovor) primalac.primi();
    }

    public Odgovor vratiEvidencijeCasova(Zahtev zahtev) throws Exception {
        posiljalac.posalji(zahtev);
        System.out.println("Poslat zahtev za vracanje svih evidencija casova...");
        return (Odgovor) primalac.primi();
    }

    public Odgovor vratiProfesore(Zahtev zahtev) throws Exception {
        posiljalac.posalji(zahtev);
        System.out.println("Poslat zahtev za vracanje svih profesora...");
        return (Odgovor) primalac.primi();
    }

    public Odgovor vratiCasove(Zahtev zahtev) throws Exception {
        posiljalac.posalji(zahtev);
        System.out.println("Poslat zahtev za vracanje svih casova...");
        return (Odgovor) primalac.primi();
    }

    public Odgovor ubaciEvidencijuCasova(Zahtev zahtev) throws Exception {
        posiljalac.posalji(zahtev);
        System.out.println("Poslat zahtev za ubacivanje nove evidencije casova...");
        return (Odgovor) primalac.primi();
    }

    public Odgovor promeniEvidencijuCasova(Zahtev zahtev) throws Exception {
        posiljalac.posalji(zahtev);
        System.out.println("Poslat zahtev za promenu evidencije casova...");
        return (Odgovor) primalac.primi();
    }

    public Odgovor pretraziEvidencijeCasova(Zahtev zahtev) throws Exception {
        posiljalac.posalji(zahtev);
        System.out.println("Poslat zahtev za pretrazivanje evidencije casova...");
        return (Odgovor) primalac.primi();
    }

    public Odgovor ubaciSertifikat(Zahtev zahtev) throws Exception {
        posiljalac.posalji(zahtev);
        System.out.println("Poslat zahtev za ubacivanje sertifikata...");
        return (Odgovor) primalac.primi();
    }

    public Odgovor vratiEvidencijuCasova(Zahtev zahtev) throws Exception {
        posiljalac.posalji(zahtev);
        System.out.println("Poslat zahtev za vracanje jedne evidencije casova...");
        return (Odgovor) primalac.primi();
    }

    public Odgovor vratiUcenika(Zahtev zahtev) throws Exception {
        posiljalac.posalji(zahtev);
        System.out.println("Poslat zahtev za vracanje jednog ucenika...");
        return (Odgovor) primalac.primi();
    }

    public Odgovor odjaviProfesora(Zahtev zahtev) throws Exception {
        posiljalac.posalji(zahtev);
        System.out.println("Poslat zahtev za odjavu profesora...");
        return (Odgovor) primalac.primi();
    }
}
