package org.main;

import org.background.TileManager;
import org.entity.Player;
import org.objects.SuperObject;

import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainJPanel extends JPanel implements Runnable {
    final int originalTileSize = 48;
    final int scale = 1;

    public final int finalSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = finalSize * maxScreenCol;
    public final int screenHeight = finalSize * maxScreenRow;

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public int gameState = 0;
    public final int menuState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int endState = 3;
    int FPS = 60;
    Sound music = new Sound();
    Sound soundEffect = new Sound();
    KeyHandler keyHandler = new KeyHandler(this);
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread mainThread;
    public Player player = new Player(this, keyHandler);
    TileManager tileManager = new TileManager(this);
    public SuperObject object[] = new SuperObject[10];
    public MainJPanel() throws IOException {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
//        this.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }
    public void setUpGame(){
        playMusic(0);
        assetSetter.setObjects();
        gameState=playState;
    }
    public void startGameThread() {
        mainThread = new Thread(this);
        mainThread.start();
    }
    public void stopGameThread(){
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
        if(gameState == playState) {
            player.update();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileManager.draw(g2);
        for (int i = 0; i <object.length; i++){
            if(object[i]!=null){
                object[i].draw(g2,this);
            }
        }
        player.draw(g2);
        ui.draw(g2);
        g2.dispose();
    }
    public void playMusic(int i){
        music.setFile(i);
        FloatControl gainControl =
                (FloatControl) music.clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-20.0f);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }
    public void playEventMusic(int i){
        soundEffect.setFile(i);
        soundEffect.play();
    }
}
