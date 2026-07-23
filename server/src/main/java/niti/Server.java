/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Una
 */
public class Server extends Thread {

    private ServerSocket serverSoket;
    private List<ObradaKlijentskihZahteva> klijenti = new ArrayList<>();

    @Override
    public void run() {
        try {
            serverSoket = new ServerSocket(9000);
            System.out.println("Soket na serveru je pokrenut!");
            while (!serverSoket.isClosed()) {
                Socket s = serverSoket.accept();
                System.out.println("Klijent je povezan!");

                ObradaKlijentskihZahteva okz = new ObradaKlijentskihZahteva(s);
                okz.start();
                klijenti.add(okz);
            }
        } catch (IOException ex) {
            if (serverSoket.isClosed()) {
                zaustaviKlijente();
                System.out.println("Server je zaustavljen!");
            } else {
                ex.printStackTrace();
            }
        }
    }

    private void zaustaviKlijente() {
        for (ObradaKlijentskihZahteva okz : klijenti) {
            okz.prekini();
        }
        klijenti.clear();
    }

    public void zaustaviServer() {
        if (serverSoket != null && !serverSoket.isClosed()) {
            try {
                serverSoket.close();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}