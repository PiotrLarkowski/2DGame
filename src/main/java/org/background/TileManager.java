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
        int col = 0;
        int row =0;
        int x = 0;
        int y = 0;
         while(col <gp.maxScreenCol && row < gp.maxScreenRow){
             g2.drawImage(tile[0].image,x,y,gp.finalSize,gp.finalSize,null);
             col++;
             x += gp.finalSize;
             if(col == gp.maxScreenCol){
                 col = 0;
                 x = 0;
                 row++;
                 y+=gp.finalSize;
             }
         }
    }
}
