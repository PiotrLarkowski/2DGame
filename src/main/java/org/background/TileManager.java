package org.background;

import org.example.MainJPanel;

import javax.imageio.ImageIO;
import java.awt.*;

public class TileManager {
    MainJPanel gp;
    Tile[] tile;
    public TileManager(MainJPanel mainJPanel){
        this.gp = mainJPanel;
        tile = new Tile[10];
        getTileImages();
    }
    public void getTileImages(){
        try{
            tile[0]=new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/backgrounds/flor.png"));
            tile[1]=new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/backgrounds/flor.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 16; j++) {
                g2.drawImage(tile[0].image,j*48,i*48,gp.finalSize,gp.finalSize,null);
            }
        }
    }
}
