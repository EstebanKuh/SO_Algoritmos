/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.userInterface_logic.views;

import business.Algoritmos.AlgoritmoFCFS;
import business.Algoritmos.AlgoritmoPrioridad;
import business.Algoritmos.AlgoritmoRR;
import business.Algoritmos.AlgoritmoSJF;
import business.Algoritmos.AlgoritmoSRTF;
import business.Algoritmos.PrioridadPreferente;
import business.Procesos.proceso;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import org.edisoncor.gui.button.ButtonAero;
import org.edisoncor.gui.button.ButtonAeroRound;
import org.edisoncor.gui.label.LabelMetric;
import org.edisoncor.gui.label.LabelRound;
import org.edisoncor.gui.panel.Panel;
import org.edisoncor.gui.textField.TextField;
import org.jfree.ui.RefineryUtilities;
import presentation.userInterface_generated.SchedulerJPanel;
import presentation.userInterface_logic.table_managers.TableGraphicManager;
import presentation.userInterface_logic.table_managers.TableProcessManager;
import presentation.userInterface_logic.views.forms.FormAlgorithmView;
import test.GanttDemo1;

/**
 *
 * @author Esteban Kuh
 */
public class MenuSchedulerView implements ActionListener {

    public static ArrayList process = new ArrayList();
    public static int idProcess = 1;
    private ArrayList tableComplete = new ArrayList();
    private String algorithmType;
    private SchedulerJPanel schedulerView;
    private ButtonAero acceptButton;
    private ButtonAeroRound acceptQuantumButton;
    private ButtonAeroRound addProcessButton;
    private ButtonAeroRound selfAddProcessButton;
    private LabelMetric algorithmName;
    private LabelRound averageTE;
    private LabelRound averageTT;
    private ButtonAero cancelButton;
    private ButtonAero clearTableButton;
    private Panel graphicPanel;
    private JTable processTable;
    private JTable processGraphicTable;
    public static TextField quantumField;
    private Panel quantumPanel;
    private TableProcessManager tableManager;
    private TableGraphicManager tableGraphicManager;

    MenuSchedulerView(String algorithm) {
        //TODO: crear clase de excepciones y mensajes
        try {
            schedulerView = new SchedulerJPanel();
            initializeComponentsViewByAlgorithm(algorithm);
            initializeManagers();
            addActionListenersToComponentsView();
            initializeTableValues();
            schedulerView.setVisible(true);
        } catch (NullPointerException exception) {
            JOptionPane.showMessageDialog(null, "Por el momento no está disponible este algoritmo");
            System.out.println(exception.fillInStackTrace());
            new MainView();
        }

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object pressedButton = event.getSource();

        if (acceptButton == pressedButton) {
            actionAccept();
        }

        if (cancelButton == pressedButton) {
            showPreviousView();
        }

        if (clearTableButton == pressedButton) {
            clearTables();
        }

        if (addProcessButton == pressedButton) {
            actionAddProcess();
        }

        if (selfAddProcessButton == pressedButton) {
            actionSelfAddProcess();
        }

        if (acceptQuantumButton == pressedButton) {
            if (!quantumField.getText().isEmpty()) {
                quantumField.setEditable(false);
                acceptQuantumButton.setEnabled(false);
            }
        }
    }

    private void initializeComponentsViewByAlgorithm(String algorithm) {
        initializeComponentsView();
        algorithmType = algorithm;
        processGraphicTable.setVisible(false);
        switch (algorithm) {
            case "FCFS":
                hideQuantumComponents();
                addAttributesComponentsFCFS();
                break;
            case "PRIORITY":
                hideQuantumComponents();
                addAttributesComponentsPriority();
                break;
            case "PRIORITYDF":
                hideQuantumComponents();
                addAttributesComponentsPriorityDF();
                break;
            case "SJF":
                hideQuantumComponents();
                addAttributesComponentsSJF();
                break;
            case "SRTF":
                hideQuantumComponents();
                addAttributesComponentsSRTF();
                break;
            case "RR":
                addAttributesComponentsRR();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Problema con selección de Algoritmo");
                break;
        }

    }

    private void initializeComponentsView() {
        this.acceptButton = schedulerView.acceptButton;
        this.addProcessButton = schedulerView.addButton;
        this.acceptQuantumButton = schedulerView.acceptQuantumButton;
        this.selfAddProcessButton = schedulerView.selfAddButton;
        this.algorithmName = schedulerView.algorithmNameLabel;
        this.averageTE = schedulerView.teAverageLabel;
        this.averageTT = schedulerView.ttAverageLabel;
        this.cancelButton = schedulerView.returnButton;
        this.graphicPanel = schedulerView.graphicPanel;
        this.processTable = schedulerView.processTable;
        this.processGraphicTable = schedulerView.processGraphicTable;
        this.quantumField = schedulerView.quantumField;
        this.quantumPanel = schedulerView.quantumPanel;
        this.clearTableButton = schedulerView.clearTablesButton;
    }

    private void addActionListenersToComponentsView() {
        acceptButton.addActionListener(this);
        addProcessButton.addActionListener(this);
        acceptQuantumButton.addActionListener(this);
        selfAddProcessButton.addActionListener(this);
        cancelButton.addActionListener(this);
        clearTableButton.addActionListener(this);
    }

    private void addAttributesComponentsFCFS() {
        algorithmName.setText("First Come First Served");
        algorithmName.setToolTipText("Primero en llegar Primero en ser servido");
    }

    private void addAttributesComponentsPriority() {
        algorithmName.setText("Prioridad Sin Derecho Preferente");
        algorithmName.setToolTipText("Prioridad sin DF");
    }

    private void addAttributesComponentsPriorityDF() {
        algorithmName.setText("Prioridad Con Derecho Preferente");
        algorithmName.setToolTipText("Prioridad con DF");
    }

    private void addAttributesComponentsRR() {
        algorithmName.setText("Round Robin");
        algorithmName.setToolTipText("RR");
    }

    private void addAttributesComponentsSJF() {
        algorithmName.setText("Shortest Job First");
        algorithmName.setToolTipText("Trabajo Màs Corto Primero");
    }

    private void addAttributesComponentsSRTF() {
        algorithmName.setText("Shortest Remaining Time First");
        algorithmName.setToolTipText("Tiempo Restante Más Corto Primero");
    }

    private void hideQuantumComponents() {
        quantumField.setVisible(false);
        quantumPanel.setVisible(false);
    }

    private void showPreviousView() {
        new MainView();
        clearTables();
        schedulerView.dispose();
    }

    private void actionAddProcess() {
        selfAddProcessButton.setEnabled(false);
        if (quantumField.isVisible()) {
            if (quantumField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Primero ingrese el Quantum");
            } else {
                if(quantumField.isEditable()){
                    JOptionPane.showMessageDialog(null, "Click en Aceptar Q. para establecer Quantum");
                }else{
                    new FormAlgorithmView(algorithmType);
                }
            }
        } else {
            new FormAlgorithmView(algorithmType);
        }
    }

    private void actionSelfAddProcess() {
        quantumField.setEditable(false);
        addProcessButton.setEnabled(false);
        acceptQuantumButton.setEnabled(false);
        new FormAlgorithmView();
    }

    private void initializeManagers() {
        tableManager = new TableProcessManager(processTable, algorithmType);
        tableGraphicManager = new TableGraphicManager(processGraphicTable, algorithmType);
    }

    public void initializeTableValues(ArrayList process) {
        tableGraphicManager.setModel(process);
        tableManager.setModel(process);
    }

    public void initializeTableValues() {
        tableGraphicManager.setModel(process);
        tableManager.setModel(tableComplete);
    }

    private void actionAccept() {
        preProcesarDatos(process);
        runAlgorithmByAlgorithm();
    }

    private void preProcesarDatos(ArrayList<proceso> lista_arrival) {
        for (int i = 0; i < lista_arrival.size() - 1; i++) {
            for (int j = i + 1; j < lista_arrival.size(); j++) {
                proceso p1 = lista_arrival.get(i);
                proceso p2 = lista_arrival.get(j);
                if (p2.Evalua_llegada(p1)) {
                    lista_arrival.set(i, p2);
                    lista_arrival.set(j, p1);
                }
            }
        }
    }

    private void runAlgorithmByAlgorithm() {
        System.out.println("IDPROCESS_GLOBAL : " + idProcess + " Process_Array : " + process);
        switch (algorithmType) {
            case "PRIORITY":
                AlgoritmoPrioridad prioridad1 = new AlgoritmoPrioridad(process);
                prioridad1.IniciaSimulacion();
                prioridad1.imprimir();
                break;
            case "PRIORITYDF":
                PrioridadPreferente prioridad2 = new PrioridadPreferente(process);
                prioridad2.IniciaSimulacion();
                prioridad2.imprimir();
                break;
            case "SJF":
                AlgoritmoSJF SJF = new AlgoritmoSJF(process);
                SJF.IniciaSimulacion();
                SJF.imprimir();
                break;
            case "SRTF":
                AlgoritmoSRTF SRJF = new AlgoritmoSRTF(process);
                SRJF.IniciaSimulacion();
                SRJF.imprimir();
                break;
            case "FCFS":
                AlgoritmoFCFS FCFS = new AlgoritmoFCFS(process);
                FCFS.IniciaSimulacion();
                FCFS.imprimir();
                break;
            case "RR":
                AlgoritmoRR RR = new AlgoritmoRR(process);
                RR.IniciaSimulacion();
                RR.imprimir();
                break;
            default:
                System.out.println("PROBLEMAS INTERNOS EJECUTANGO ALGORITMO");
                break;
        }
        processGraphicTable.setVisible(true);
        clearProcessTable();
    }

    public void updateTable(ArrayList<proceso> lista_imprimir, double PromedioTT, double PromedioTE) {
        String promedioEspera = String.valueOf(PromedioTE);
        String promedioTotal = String.valueOf(PromedioTT);

        updateGraphicTable(lista_imprimir);
        //createGanttChart(lista_imprimir);
        averageTE.setText(promedioEspera);
        averageTT.setText(promedioTotal);
    }

    private void clearProcessTable() {
        process.clear();
        updateProcessTable(process);
        idProcess = 1;
        enableAttributes();
    }

    private void clearTables() {
        process.clear();
        updateProcessTable(process);
        updateGraphicTable(process);
        idProcess = 1;
        enableAttributes();
    }

    private void enableAttributes(){
        addProcessButton.setEnabled(true);
        selfAddProcessButton.setEnabled(true);
        quantumField.setEditable(true);
        acceptQuantumButton.setEnabled(true);
    }
    private void updateGraphicTable(ArrayList<proceso> lista_imprimir) {
        tableGraphicManager.setModel(lista_imprimir);
    }

    public void updateProcessTable(ArrayList<proceso> lista_imprimir) {
        tableManager.setModel(lista_imprimir);
    }

    private void createGanttChart(ArrayList<proceso> lista_imprimir) {
        GanttDemo1 ganttChart = new GanttDemo1(lista_imprimir,algorithmType);
        ganttChart.pack();
        RefineryUtilities.centerFrameOnScreen(ganttChart);
        ganttChart.setVisible(true);
    }

}
