/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segta.clases;

/**
 *
 * @author Quales Group
 */
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableCellRenderer;

public class TimestampCellRenderer extends DefaultTableCellRenderer {

    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public TimestampCellRenderer() {
        super();
    }

    public void setValue(Object value) {
        if (formatter == null) {
            formatter = DateFormat.getDateInstance();
        }
        setText((value == null) ? "" : formatter.format(value));
    }
}

