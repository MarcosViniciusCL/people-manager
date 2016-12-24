/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people.manager.tools;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Calendar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import people.manager.controller.Controller;

public class ProdutoTableCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component result = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (isSelected) {
            result.setFont(new Font("arial", Font.BOLD, 12));
            result.setForeground(Color.white);
            result.setBackground(Color.blue);
        } else {
            int quant = Integer.parseInt((String) table.getValueAt(row, 4));
            int min_est = Integer.parseInt(Controller.getConfiguracao().getProperty("quantidade.min_estoque"));
            if (quant == 0) {
                result.setFont(new Font("arial", Font.PLAIN, 12));
                result.setForeground(Color.black);
                result.setBackground(Color.red);
            } else if (quant > 0 && quant < min_est) {
                result.setFont(new Font("arial", Font.PLAIN, 12));
                result.setForeground(Color.black);
                result.setBackground(Color.yellow);
            } else {
                result.setFont(new Font("arial", Font.PLAIN, 12));
                result.setForeground(Color.black);
                result.setBackground(Color.white);
            }
        }

        return result;
    }

//     @Override
//     public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//        Color c = Color.WHITE;
//        Object text = table.getValueAt(row, 3);
//        if (text != null && "VERMELHO".equals(text.toString()))
//            c = Color.RED;
//        label.setBackground(c);
//        return label;
//    }
}
