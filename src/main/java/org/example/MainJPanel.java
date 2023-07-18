package org.example;

import org.entity.Player;

import javax.swing.*;
import java.awt.*;

public class MainJPanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;

    public final int finalSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = finalSize * maxScreenCol;
    final int screenHeight = finalSize * maxScreenRow;
    int FPS = 60;
    Thread mainThread;
    KeyHandler keyHandler = new KeyHandler();
    Player player = new Player(this, keyHandler);
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public MainJPanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        mainThread = new Thread(this);
        mainThread.start();
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (mainThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        player.draw(g2);
        g2.dispose();
    }
}
