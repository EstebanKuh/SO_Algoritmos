/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.userInterface_logic.table_managers;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Esteban Kuh
 */
public class TableManager {
    
     protected JTable table;
    protected DefaultTableModel tableModel;

    protected TableManager(JTable table) {
        this.table = table;
    }

    public boolean isRowSelected(){
        final int NONE_SELECTED_ROW=-1; 
        if (getSelectedRow() != NONE_SELECTED_ROW){
            return true;
        }  return false;
    }
    
    public int getIdOfSelectedRow(){
        final int COLUMN_ID=0;
        int row =getSelectedRow();
        int id=(int)table.getValueAt(row, COLUMN_ID);
        return  id;
    }
    
    protected void clearModel(){
        final int ANY_ROW=0;
        tableModel.setRowCount(ANY_ROW);
    }
    
    protected void addRow(Object[] row){
        tableModel.addRow(row);
    }
    
    protected void setModel(){
        table.setModel(tableModel);
    }
    
    protected void createTableModel(String[] columnNames){
        final int ROW_COUNT=0;
        tableModel = new DefaultTableModel(columnNames, ROW_COUNT){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }
    
    protected int getSelectedRow(){
        return table.getSelectedRow();
    }
    
}
