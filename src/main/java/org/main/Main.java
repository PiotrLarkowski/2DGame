package org.main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("GAME");

        MainJPanel mainJPanel = new MainJPanel();
        window.add(mainJPanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        mainJPanel.setUpGame();
        mainJPanel.startGameThread();

    }
}