package niti;

import controller.ControllerServer;
import domen.Cas;
import domen.EvidencijaCasova;
import domen.PlesniNivo;
import domen.Profesor;
import domen.Sertifikat;
import domen.Ucenik;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Odgovor;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;
/**
 * 
 * @author Una
 */

public class ObradaKlijentskihZahteva extends Thread {

    private Socket socket;
    private Posiljalac posiljalac;
    private Primalac primalac;
    private Profesor profesor;

    public ObradaKlijentskihZahteva(Socket socket) {
        this.socket = socket;
        this.posiljalac = new Posiljalac(socket);
        this.primalac = new Primalac(socket);
    }

    @Override
    public void run() {
        while (!socket.isClosed()) {
            try {
                Zahtev zahtev = (Zahtev) primalac.primi();
                Odgovor odgovor = obradiZahtev(zahtev);
                posiljalac.posalji(odgovor);
            } catch (Exception ex) {
                if (socket.isClosed()) {
                    System.out.println("Zatvoren klijentski soket od strane servera!");
                } else {
                    prekini();
                    System.out.println("Klijent se sam odvezao!");
                }
            }
        }
    }

    private Odgovor obradiZahtev(Zahtev zahtev) {
        switch (zahtev.getOperacija()) {
            case LOGIN:
                return login(zahtev);
            case VRATI_LISTU_UCENIK:
                return vratiListuUcenik(zahtev);
            case UBACI_UCENIK:
                return ubaciUcenik(zahtev);
            case PROMENI_UCENIK:
                return promeniUcenik(zahtev);
            case OBRISI_UCENIK:
                return obrisiUcenik(zahtev);
            case PRETRAZI_UCENIK:
                return pretraziUcenik(zahtev);
            case VRATI_UCENIK:
                return vratiUcenik(zahtev);
            case VRATI_LISTU_PLESNI_NIVO:
                return vratiListuPlesniNivo(zahtev);
            case VRATI_LISTU_EVIDENCIJA_CASOVA:
                return vratiListuEvidencijaCasova(zahtev);
            case UBACI_EVIDENCIJA_CASOVA:
                return ubaciEvidencijaCasova(zahtev);
            case PROMENI_EVIDENCIJA_CASOVA:
                return promeniEvidencijaCasova(zahtev);
            case PRETRAZI_EVIDENCIJA_CASOVA:
                return pretraziEvidencijaCasova(zahtev);
            case VRATI_EVIDENCIJA_CASOVA:
                return vratiEvidencijaCasova(zahtev);
            case VRATI_LISTU_PROFESOR:
                return vratiListuProfesor(zahtev);
            case VRATI_LISTU_CAS:
                return vratiListuCas(zahtev);
            case UBACI_SERTIFIKAT:
                return ubaciSertifikat(zahtev);
            default:
                throw new AssertionError();
        }
    }

    public void prekini() {
        if (socket != null && !socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // LOGIN
    private Odgovor login(Zahtev zahtev) {
        Odgovor odgovor = new Odgovor();
        try {
            Profesor p = ControllerServer.getInstance().login((Profesor) zahtev.getParametar());
            System.out.println("Uspesna prijava na sistem...");
            this.profesor = p;
            odgovor.setOdgovor(p);
        } catch (Exception ex) {
            ex.printStackTrace();
            odgovor.setEx(ex);
        }
        return odgovor;
    }

    // UCENIK
    private Odgovor vratiListuUcenik(Zahtev zahtev) {
        Odgovor odgovor = new Odgovor();
        try {
            List<Ucenik> ucenici = ControllerServer.getInstance().vratiListuUcenik();
            System.out.println("Uspesno ucitana lista ucenika...");
            odgovor.setOdgovor(ucenici);
        } catch (Exception ex) {
            ex.printStackTrace();
            odgovor.setEx(ex);
        }
        return odgovor;
    }

    private Odgovor ubaciUcenik(Zahtev zahtev) {
        Odgovor odgovor = new Odgovor();
        try {
            ControllerServer.getInstance().ubaciUcenik((Ucenik) zahtev.getParametar());
            System.out.println("Uspesno ubacen novi ucenik...");
            odgovor.setOdgovor(null);
        } catch (Exception ex) {
            ex.printStackTrace();
            odgovor.setEx(ex);
        }
        return odgovor;
    }

    private Odgovor promeniUcenik(Zahtev zahtev) {
        Odgovor odgovor = new Odgovor();
        try {
            ControllerServer.getInstance().promeniUcenik((Ucenik) zahtev.getParametar());
            System.out.println("Uspesno promenjen ucenik...");
            odgovor.setOdgovor(null);
        } catch (Exception ex) {
            ex.printStackTrace();
            odgovor.setEx(ex);
        }
        return odgovor;
    }

    private Odgovor obrisiUcenik(Zahtev zahtev) {
        Odgovor odgovor = new Odgovor();
        try {
            ControllerServer.getInstance().obrisiUcenik((Ucenik) zahtev.getParametar());
            System.out.println("Uspesno obrisan ucenik...");
            odgovor.setOdgovor(null);
        } catch (Exception ex) {
            ex.printStackTrace();
            odgovor.setEx(ex);
        }
        return odgovor;
    }

    private Odgovor pretraziUcenik(Zahtev zahtev) {
        Odgovor odgovor = new Odgovor();
        try {
            List<Ucenik> ucenici = ControllerServer.getInstance().pretraziUcenik((Ucenik) zahtev.getParametar());
            System.out.println("Uspesno pretrazeni ucenici...");
            odgovor.setOdgovor(ucenici);
        } catch (Exception ex) {
            ex.printStackTrace();
            odgovor.setEx(ex);
        }
        return odgovor;
    }

    private Odgovor vratiUcenik(Zahtev zahtev) {
        Odgovor odgovor = new Odgovor();
        try {
            Ucenik u = ControllerServer.getInstance().vratiUcenik((Ucenik) zahtev.getParametar());
            System.out.println("Uspesno vracen ucenik...");
            odgovor.setOdgovor(u);
        } catch (Exception ex) {
            ex.printStackTrace();
            odgovor.setEx(ex);
        }
        return odgovor;
    }

    // PLESNI NIVO
    private Odgovor vratiListuPlesniNivo(Zahtev zahtev) {
        Odgovor odgovor = new Odgovor();
        try {
            List<PlesniNivo> nivoi = ControllerServer.getInstance().vratiListuPlesniNivo();
            System.out.println("Uspesno ucitana lista plesnih nivoa...");
            odgovor.setOdgovor(nivoi);
        } catch (Exception ex) {
            ex.printStackTrace();
            odgovor.setEx(ex);
        }
        return odgovor;
    }

    // EVIDENCIJA CASOVA
    private Odgovor vratiListuEvidencijaCasova(Zahtev zahtev) {
        Odgovor odgovor = new Odgovor();
        try {
            List<EvidencijaCasova> evidencije = ControllerServer.getInstance().vratiListuEvidencijaCasova();
            System.out.println("Uspesno ucitana lista evidencija casova...");
            odgovor.setOdgovor(evidencije);
        } catch (Exception ex) {
            ex.printStackTrace();
            odgovor.setEx(ex);
        }
        return odgovor;
    }

    private Odgovor ubaciEvidencijaCasova(Zahtev zahtev) {
        Odgovor odgovor = new Odgovor();
        try {
            ControllerServer.getInstance().ubaciEvidencijaCasova((EvidencijaCasova) zahtev.getParametar());
            System.out.println("Uspesno ubacena nova evidencija casova...");
            odgovor.setOdgovor(null);
        } catch (Exception ex) {
            ex.printStackTrace();
            odgovor.setEx(ex);
        }
        return odgovor;
    }

    private Odgovor promeniEvidencijaCasova(Zahtev zahtev) {
        Odgovor odgovor = new Odgovor();
        try {
            ControllerServer.getInstance().promeniEvidencijaCasova((EvidencijaCasova) zahtev.getParametar());
            System.out.println("Uspesno promenjena evidencija casova...");
            odgovor.setOdgovor(null);
        } catch (Exception ex) {
            ex.printStackTrace();
            odgovor.setEx(ex);
        }
        return odgovor;
    }

    private Odgovor pretraziEvidencijaCasova(Zahtev zahtev) {
        Odgovor odgovor = new Odgovor();
        try {
            List<EvidencijaCasova> evidencije = ControllerServer.getInstance().pretraziEvidencijaCasova((EvidencijaCasova) zahtev.getParametar());
            System.out.println("Uspesno pretrazene evidencije casova...");
            odgovor.setOdgovor(evidencije);
        } catch (Exception ex) {
            ex.printStackTrace();
            odgovor.setEx(ex);
        }
        return odgovor;
    }

    private Odgovor vratiEvidencijaCasova(Zahtev zahtev) {
        Odgovor odgovor = new Odgovor();
        try {
            EvidencijaCasova ec = ControllerServer.getInstance().vratiEvidencijaCasova((EvidencijaCasova) zahtev.getParametar());
            System.out.println("Uspesno vracena evidencija casova...");
            odgovor.setOdgovor(ec);
        } catch (Exception ex) {
            ex.printStackTrace();
            odgovor.setEx(ex);
        }
        return odgovor;
    }

    // PROFESOR
    private Odgovor vratiListuProfesor(Zahtev zahtev) {
        Odgovor odgovor = new Odgovor();
        try {
            List<Profesor> profesori = ControllerServer.getInstance().vratiListuProfesor();
            System.out.println("Uspesno ucitana lista profesora...");
            odgovor.setOdgovor(profesori);
        } catch (Exception ex) {
            ex.printStackTrace();
            odgovor.setEx(ex);
        }
        return odgovor;
    }

    // CAS
    private Odgovor vratiListuCas(Zahtev zahtev) {
        Odgovor odgovor = new Odgovor();
        try {
            List<Cas> casovi = ControllerServer.getInstance().vratiListuCas();
            System.out.println("Uspesno ucitana lista casova...");
            odgovor.setOdgovor(casovi);
        } catch (Exception ex) {
            ex.printStackTrace();
            odgovor.setEx(ex);
        }
        return odgovor;
    }

    // SERTIFIKAT
    private Odgovor ubaciSertifikat(Zahtev zahtev) {
        Odgovor odgovor = new Odgovor();
        try {
            ControllerServer.getInstance().ubaciSertifikat((Sertifikat) zahtev.getParametar());
            System.out.println("Uspesno ubacen sertifikat...");
            odgovor.setOdgovor(null);
        } catch (Exception ex) {
            ex.printStackTrace();
            odgovor.setEx(ex);
        }
        return odgovor;
    }
}