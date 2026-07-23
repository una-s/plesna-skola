package modeli;

import domen.Ucenik;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Una
 */
public class ModelTabeleUcenik extends AbstractTableModel {

    private List<Ucenik> ucenici;
    private String[] kolone = {"Ime", "Prezime", "Broj telefona", "Plesni nivo"};

    public ModelTabeleUcenik(List<Ucenik> ucenici) {
        this.ucenici = ucenici;
    }

    @Override
    public int getRowCount() {
        return ucenici.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return ucenici.get(rowIndex).getIme();
            case 1:
                return ucenici.get(rowIndex).getPrezime();
            case 2:
                return ucenici.get(rowIndex).getBrojTelefona();
            case 3:
                return ucenici.get(rowIndex).getPlesniNivo().getNivo();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Ucenik> getUcenici() {
        return ucenici;
    }
}