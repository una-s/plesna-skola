package repository.db.impl;

import repository.db.DbRepository;
import domen.ApstraktniDomenskiObjekat;
import java.util.List;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import repository.db.DbConnectionFactory;

/**
 *
 * @author Una
 */
public class DbRepositoryGeneric implements DbRepository<ApstraktniDomenskiObjekat> {

    @Override
    public List<ApstraktniDomenskiObjekat> getAll(ApstraktniDomenskiObjekat param) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();

            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM ").append(param.vratiNazivTabele())
                    .append(param.alias()).append(param.join());

            if (param.uslovZaSelect() != null && !param.uslovZaSelect().isEmpty()) {
                sb.append(" WHERE ").append(param.uslovZaSelect());
            }

            String upit = sb.toString();
            System.out.println(upit);

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(upit);

            List<ApstraktniDomenskiObjekat> lista = param.vratiListu(rs);

            rs.close();
            stmt.close();

            return lista;

        } catch (SQLException ex) {
            System.out.println("Greska pri metodi getAll: " + ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public void add(ApstraktniDomenskiObjekat param) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();

            StringBuilder upit = new StringBuilder();
            upit.append("INSERT INTO ").append(param.vratiNazivTabele()).append(" (")
                    .append(param.vratiKoloneZaUbacivanje()).append(") VALUES (")
                    .append(param.vratiVrednostZaUbacivanje()).append(")");

            System.out.println(upit);

            Statement stmt = connection.createStatement();
            stmt.executeUpdate(upit.toString(), Statement.RETURN_GENERATED_KEYS);

            ResultSet generisaniKljucevi = stmt.getGeneratedKeys();
            if (generisaniKljucevi.next()) {
                param.postaviId(generisaniKljucevi.getLong(1));
            }

            generisaniKljucevi.close();
            stmt.close();

        } catch (SQLException ex) {
            System.out.println("Greska pri metodi add: " + ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public void edit(ApstraktniDomenskiObjekat param) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();

            String upit = "UPDATE " + param.vratiNazivTabele()
                    + " SET " + param.vratiVrednostZaIzmenu()
                    + " WHERE " + param.vratiPrimarniKljuc();

            System.out.println(upit);

            Statement stmt = connection.createStatement();
            stmt.executeUpdate(upit);
            stmt.close();

        } catch (SQLException ex) {
            System.out.println("Greska pri metodi edit: " + ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public void delete(ApstraktniDomenskiObjekat param) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();

            String upit = "DELETE FROM " + param.vratiNazivTabele()
                    + " WHERE " + param.vratiPrimarniKljuc();

            System.out.println(upit);

            Statement stmt = connection.createStatement();
            stmt.executeUpdate(upit);
            stmt.close();

        } catch (SQLException ex) {
            System.out.println("Greska pri metodi delete: " + ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public ApstraktniDomenskiObjekat get(ApstraktniDomenskiObjekat param) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();

            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM ").append(param.vratiNazivTabele())
                    .append(param.alias()).append(param.join());

            if (param.uslovZaSelect() != null && !param.uslovZaSelect().isEmpty()) {
                sb.append(" WHERE ").append(param.uslovZaSelect());
            }

            String upit = sb.toString();
            System.out.println(upit);

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(upit);

            ApstraktniDomenskiObjekat rezultat = param.vratiObjekatIzRS(rs);

            rs.close();
            stmt.close();

            return rezultat;

        } catch (SQLException ex) {
            System.out.println("Greska pri metodi get: " + ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }
    }
}
