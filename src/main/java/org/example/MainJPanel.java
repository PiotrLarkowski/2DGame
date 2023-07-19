package org.example;

import org.background.TileManager;
import org.entity.Player;

import javax.swing.*;
import java.awt.*;

public class MainJPanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;

    public final int finalSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = finalSize * maxScreenCol;
    public final int screenHeight = finalSize * maxScreenRow;

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = finalSize & maxWorldCol;
    public final int worldHeight = finalSize & maxWorldRow;
    int FPS = 60;
    Thread mainThread;
    KeyHandler keyHandler = new KeyHandler();
    public Player player = new Player(this, keyHandler);
    TileManager tileManager = new TileManager(this);

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
        tileManager.draw(g2);
        player.draw(g2);
        g2.dispose();
    }
}
