/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.userInterface_logic.views.forms;

import business.Procesos.proceso;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import org.edisoncor.gui.button.ButtonAero;
import org.edisoncor.gui.label.LabelRect;
import org.edisoncor.gui.textField.TextField;
import presentation.userInterface_generated.FormAlgorithmJPanel;
import static presentation.userInterface_logic.views.MenuSchedulerView.process;
import static presentation.userInterface_logic.views.MenuSchedulerView.idProcess;
import static presentation.userInterface_logic.views.MainView.menuSchedulerView;
import static presentation.userInterface_logic.views.MenuSchedulerView.quantumField;

/**
 *
 * @author Esteban Kuh
 */
public class FormAlgorithmView implements ActionListener {

    private FormAlgorithmJPanel formAlgorithmView;
    private ButtonAero acceptButton;
    private ButtonAero cancelButton;
    private LabelRect arriveLabel;
    private LabelRect priorityLabel;
    private TextField arriveField;
    private TextField priorityField;
    private TextField burstField;
    private boolean havePriority = true;
    private boolean haveQuantum = false;

    public FormAlgorithmView(String algorithm) {
        switch (algorithm) {
            case "FCFS":
                havePriority = false;
                break;
            case "PRIORITY":
                break;
            case "PRIORITYDF":
                break;
            case "SJF":
                havePriority = false;
                break;
            case "SRTF":
                havePriority = false;
                break;
            case "RR":
                havePriority = false;
                haveQuantum = true;
                break;
            default:
                JOptionPane.showMessageDialog(null, "Problema con selección de Algoritmo FORMvIEW");
                break;
        }
        initialize();
    }

    public FormAlgorithmView() {
        addProcess();
    }

    private void initialize() {
        formAlgorithmView = new FormAlgorithmJPanel();
        initializeComponentsViewByAlgorithm();
        setAttributesComponents();
        addActionListenersToComponentsView();
        formAlgorithmView.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object pressedButton = event.getSource();

        if (acceptButton == pressedButton) {
            addPriorityProcessView();
        }

        if (cancelButton == pressedButton) {
            showPreviousView();
        }
    }

    private void initializeComponentsViewByAlgorithm() {
        this.acceptButton = formAlgorithmView.acceptButton;
        this.cancelButton = formAlgorithmView.cancelButton;
        this.arriveField = formAlgorithmView.arriveProcessField;
        this.arriveLabel = formAlgorithmView.arriveLabel;
        this.burstField = formAlgorithmView.timeProcessField;
        this.priorityField = formAlgorithmView.priorityProcessField;
        this.priorityLabel = formAlgorithmView.priorityLabel;

    }

    private void addActionListenersToComponentsView() {
        acceptButton.addActionListener(this);
        cancelButton.addActionListener(this);
    }

    private void setAttributesComponents() {
        arriveField.setVisible(true);
        arriveLabel.setVisible(true);
        priorityField.setVisible(havePriority);
        priorityLabel.setVisible(havePriority);
    }

    private void addPriorityProcessView() {
        proceso newProcess;
        int burst = Integer.parseInt(burstField.getText());
        int arrive = Integer.parseInt(arriveField.getText());
        int priority;

        if (havePriority) {
            priority = Integer.parseInt(priorityField.getText());
            newProcess = new proceso(priority, arrive, burst, idProcess++);
        } else {
            if (haveQuantum) {
                int quantum = Integer.parseInt(quantumField.getText());
                newProcess = new proceso(0, quantum, arrive, burst, idProcess++);
            } else {
                newProcess = new proceso(arrive, burst, idProcess++);
            }
        }

        process.add(newProcess);
        menuSchedulerView.updateProcessTable(process);
        setEmptyFields();
    }

    private void addProcess() {
        //prioridad,tiempo_llegada,rafaga,NoProceso
          proceso proceso1 = new proceso(5,8,14,1);
          proceso proceso2 = new proceso(2,12,22,2);
          proceso proceso3 = new proceso(8,0,8,3);
          proceso proceso4 = new proceso(5,6,16,4);
          proceso proceso5 = new proceso(7,24,26,5);
          proceso proceso6 = new proceso(9,16,24,6);
          proceso proceso7= new proceso(4,20,12,7);
          proceso proceso8 = new proceso(8,22,18,8);
       
      

        process.add(proceso1);
        process.add(proceso2);
        process.add(proceso3);
        process.add(proceso4);
        process.add(proceso5);
        process.add(proceso6);
        process.add(proceso7);
        process.add(proceso8);
       
        menuSchedulerView.updateProcessTable(process);
    }

    private void showPreviousView() {
        formAlgorithmView.dispose();
    }

    private void setEmptyFields() {
        burstField.setText("");
        arriveField.setText("");
        priorityField.setText("");
    }

}
