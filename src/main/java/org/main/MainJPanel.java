package org.main;

import org.background.TileManager;
import org.entity.Player;
import org.objects.SuperObject;
import org.entity.*;

import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MainJPanel extends JPanel implements Runnable {
    final int originalTileSize = 48;
    final int scale = 1;
    public int lifePercentage = 100;
    public int manaPercentage = 100;
    public final int finalSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = finalSize * maxScreenCol;
    public final int screenHeight = finalSize * maxScreenRow;

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public long timeGameStarted = System.currentTimeMillis();

    public Random rand = new Random();
    public int gameState = 0;
    public final int menuState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int endState = 3;
    public final int fightState = 4;
    public final int spellBookState = 5;
    public boolean themePlay = false;
    public int reasonOfDialogue = 0;
    public ArrayList<String> universalSpellBook = new ArrayList<>();
    public ArrayList<String> fightingSpellBook = new ArrayList<>();
    int FPS = 60;
    Sound music = new Sound();
    Sound soundEffect = new Sound();
    KeyHandler keyHandler = new KeyHandler(this);
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread mainThread;
    public Player player = new Player(this, keyHandler);
    static TileManager tileManager;
    public SuperObject object[] = new SuperObject[20];
    public ObjectEntity[] npcArray = new ObjectEntity[10];

    public MainJPanel(int index) throws IOException {
        tileManager = new TileManager(this, index);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
//        this.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setNewLevel(int i){
        setUpGame();
        player = new Player(this, keyHandler);
        playMusic(0);
        tileManager = new TileManager(this, i);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        repaint();
    }
    public long getSecondOfPlay(long currentTime){
        return (currentTime-timeGameStarted)/1000;
    }
    public void setUpGame() {
        assetSetter.setObjects();
        assetSetter.setNPC();
        gameState = playState;
    }

    public void startGameThread() {
        mainThread = new Thread(this);
        mainThread.start();
    }

    public void stopGameThread() {
        mainThread.interrupt();
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
        if (gameState == playState) {
            player.update();
            for (int i = 0; i < npcArray.length; i++) {
                if (npcArray[i] != null) {
                    npcArray[i].update();
                }
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileManager.draw(g2);
        for (int i = 0; i < object.length; i++) {
            if (object[i] != null) {
                object[i].draw(g2, this);
            }
        }
        for (int i = 0; i < npcArray.length; i++) {
            if (npcArray[i] != null) {
                try {
                    npcArray[i].draw(g2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        player.drawBars(g2);
        player.drawAvatar(g2);
        player.draw(g2);
        ui.draw(g2);
        g2.dispose();
    }



    public void playMusic(int i) {
        themePlay = true;
        music.setFile(i);
        FloatControl gainControl =
                (FloatControl) music.clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-20.0f);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        themePlay = false;
        music.stop();
    }

    public void playEventMusic(int i) {
        soundEffect.setFile(i);
        soundEffect.play();
    }
}
