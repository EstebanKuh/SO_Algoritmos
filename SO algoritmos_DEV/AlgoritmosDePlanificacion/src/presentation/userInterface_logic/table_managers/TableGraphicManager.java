/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.userInterface_logic.table_managers;

import business.Procesos.proceso;
import java.util.ArrayList;
import javax.swing.JTable;
import org.edisoncor.gui.label.LabelMetric;

/**
 *
 * @author Esteban Kuh
 */
public class TableGraphicManager extends TableManager {
    private final String[] COLUMN_NAMES_PRIORITY = {"Proceso", "LLego", "Ráfaga", "Prioridad", "TE", "TT"};
    private final String[] COLUMN_NAMES_SJF = {"Proceso", "LLego", "Ráfaga", "TE", "TT"};
    private final String[] COLUMN_NAMES_FCFS = {"Proceso", "LLego", "Ráfaga", "TE", "TT"};
    private final String[] COLUMN_NAMES_RR = {"Proceso", "LLego", "Ráfaga", "Quatum", "TE", "TT"};
    private String algorithmType;

    public TableGraphicManager(JTable table, String algorithmName) {
        super(table);
        algorithmType = algorithmName;
        createTableInventoryModel();
    }

    private void createTableInventoryModel() {
        switch (algorithmType) {
            case "PRIORITY":
                createTableModel(COLUMN_NAMES_PRIORITY);
                break;
            case "PRIORITYDF":
                createTableModel(COLUMN_NAMES_PRIORITY);
                break;
            case "SJF":
                createTableModel(COLUMN_NAMES_SJF);
                break;
            case "SRTF":
                createTableModel(COLUMN_NAMES_SJF);
                break;
            case "FCFS":
                createTableModel(COLUMN_NAMES_FCFS);
                break;
            case "RR":
                createTableModel(COLUMN_NAMES_RR);
                break;
            default:
                System.out.println("PROBLEMAS INTERNOS TABLA");
                break;
        }
    }

    public void setModel(ArrayList<proceso> processes) {
        clearModel();
        for (proceso process : processes) {
            addProcessRow(process);
        }
        setModel();
    }

    private void addProcessRow(proceso process) {
        Object[] processRow;
        switch (algorithmType) {
            case "PRIORITY":
                processRow = createProcessPriorityRow(process);
                break;
            case "PRIORITYDF":
                processRow = createProcessPriorityRow(process);
                break;
            case "SJF":
                processRow = createProcessSJFRow(process);
                break;
            case "SRTF":
                processRow = createProcessSJFRow(process);
                break;
            case "FCFS":
                processRow = createProcessFCFSRow(process);
                break;
            case "RR":
                processRow = createProcessRRRow(process);
                break;
            default:
                processRow = createProcessEmptyRow(process);
                System.out.println("PROBLEMAS INTERNOS");
                break;
        }
        addRow(processRow);
    }

    private Object[] createProcessPriorityRow(proceso process) {
        Object[] processRow = new Object[]{
            process.getId_proceso(),
            process.getTiempo_llegada(),
            process.getTiempo_rafaga(),
            process.getPrioridad(),
            process.getTiempo_espera(),
            process.getTiempo_total()
        };
        return processRow;
    }
    
    private Object[] createProcessSJFRow(proceso process) {
        Object[] processRow = new Object[]{
            process.getId_proceso(),
            process.getTiempo_llegada(),
            process.getTiempo_rafaga(),
            process.getTiempo_espera(),
            process.getTiempo_total()
        };
        return processRow;
    }
    
    private Object[] createProcessFCFSRow(proceso process) {
        Object[] processRow = new Object[]{
            process.getId_proceso(),
            process.getTiempo_llegada(),
            process.getTiempo_rafaga(),
            process.getTiempo_espera(),
            process.getTiempo_total()
        };
        return processRow;
    }
    
    private Object[] createProcessRRRow(proceso process) {
        Object[] processRow = new Object[]{
            process.getId_proceso(),
            process.getTiempo_llegada(),
            process.getTiempo_rafaga(),
            process.getQuantum(),
            process.getTiempo_espera(),
            process.getTiempo_total()
        };
        return processRow;
    }
    
    private Object[] createProcessEmptyRow(proceso process) {
        Object[] processRow = new Object[]{
            "No hay valores"
        };
        return processRow;
    }

}
