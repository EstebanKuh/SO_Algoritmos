/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.userInterface_logic.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.edisoncor.gui.button.ButtonAeroRight;
import org.edisoncor.gui.button.ButtonAeroRound;
import presentation.userInterface_generated.MainJPanel;

/**
 *
 * @author Esteban Kuh
 */
public class MainView implements ActionListener {

    public static MenuSchedulerView menuSchedulerView;
    
    private MainJPanel mainView;
    private ButtonAeroRound fcfsButton;
    private ButtonAeroRound priorityButton;
    private ButtonAeroRound priorityDFButton;
    private ButtonAeroRound sjfButton;
    private ButtonAeroRound srtfButton;
    private ButtonAeroRound rrButton;
    private ButtonAeroRight exitButton;

    public MainView() {
        mainView = new MainJPanel();
        initializeComponentsView();
        addActionListenersToComponentsView();
        mainView.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object pressedButton = event.getSource();

        if (exitButton == pressedButton) {
            System.exit(0);
        } else {
            if (fcfsButton == pressedButton) {
                actionFCFS();
            }
            if (priorityButton == pressedButton) {
                actionPriority();
            }
            if (priorityDFButton == pressedButton) {
                actionPriorityDF();
            }
            if (sjfButton == pressedButton) {
                actionSJF();
            }
            if (srtfButton == pressedButton) {
                actionSRTF();
            }
            if (rrButton == pressedButton) {
                actionRR();
            }
        }

    }

    private void initializeComponentsView() {
        this.fcfsButton = mainView.fcfsButton;
        this.priorityButton = mainView.priorityButton;
        this.priorityDFButton = mainView.priorityDfButton;
        this.sjfButton = mainView.sjfButton;
        this.srtfButton = mainView.srtfButton;
        this.rrButton = mainView.rrButton;
        this.exitButton = mainView.exitButton;
    }

    private void addActionListenersToComponentsView() {
        fcfsButton.addActionListener(this);
        priorityButton.addActionListener(this);
        priorityDFButton.addActionListener(this);
        sjfButton.addActionListener(this);
        srtfButton.addActionListener(this);
        rrButton.addActionListener(this);
        exitButton.addActionListener(this);
    }

    private void actionFCFS() {
        menuSchedulerView = new MenuSchedulerView("FCFS");
        mainView.dispose();
    }

    private void actionPriority() {
        menuSchedulerView =new MenuSchedulerView("PRIORITY");
        mainView.dispose();
    }

    private void actionPriorityDF() {
        menuSchedulerView = new MenuSchedulerView("PRIORITYDF");
        mainView.dispose();
    }

    private void actionSJF() {
        menuSchedulerView = new MenuSchedulerView("SJF");
        mainView.dispose();
    }

    private void actionSRTF() {
        menuSchedulerView = new MenuSchedulerView("SRTF");
        mainView.dispose();
    }

    private void actionRR() {
        menuSchedulerView = new MenuSchedulerView("RR");
        mainView.dispose();
    }

}
