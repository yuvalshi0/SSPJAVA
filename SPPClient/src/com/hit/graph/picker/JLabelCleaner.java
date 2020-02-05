package com.hit.graph.picker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

public class JLabelCleaner {

    private JLabel label;
    private int waitSeconds;

    public JLabelCleaner(int waitSeconds, JLabel label) {
        this.label = label;
        this.waitSeconds = waitSeconds;
    }

    public void startCountdownFromNow() {
        Timer timer = new Timer(waitSeconds * 1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("");
            }
        });
        timer.start();
    }
}
