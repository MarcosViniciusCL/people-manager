/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people.manager.tools;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class VendaTableCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component result = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (isSelected) {
            result.setFont(new Font("arial", Font.BOLD, 12));
            result.setForeground(Color.white);
            result.setBackground(Color.blue);
        } else {
            String estado = (String) table.getValueAt(row, 5);
            if (estado.equals("PENDENTE")) {
                result.setFont(new Font("arial", Font.PLAIN, 12));
                result.setForeground(Color.black);
                result.setBackground(Color.orange);
            } else if(estado.equals("CANCELADA")){
                result.setFont(new Font("arial", Font.PLAIN, 12));
                result.setForeground(Color.white);
                result.setBackground(Color.red);
            } else {
                result.setFont(new Font("arial", Font.PLAIN, 12));
                result.setForeground(Color.black);
                result.setBackground(Color.white);
            }
        }
        return result;
    }

}
