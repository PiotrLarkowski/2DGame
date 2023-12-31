package org.main;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static MainJPanel mainJPanel;
    public static JFrame window;

    public static void main(String[] args) throws IOException {

        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("GAME");

        mainJPanel = new MainJPanel();
        window.add(mainJPanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        mainJPanel.setUpGame();
        mainJPanel.startGameThread();

    }
}