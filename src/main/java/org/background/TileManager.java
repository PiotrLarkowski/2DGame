package org.background;

import org.main.MainJPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    MainJPanel gp;
    public Tile[] tile;

    public int mapTileNumber[][];

    public TileManager(MainJPanel mainJPanel) {
        this.gp = mainJPanel;
        tile = new Tile[10];
        getTileImages();
        mapTileNumber = new int[gp.maxWorldCol][gp.maxWorldRow];
        mapTileNumber = loadMap("/maps/worldMap01.txt");
    }

    public int[][] loadMap(String filePath) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(filePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            int col = 0;
            int row = 0;
            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = bufferedReader.readLine();
                while (col < gp.maxWorldCol) {
                    String number[] = line.split(" ");
                    int num = Integer.parseInt(number[col]);
                    if(num == 0){
                        int i = gp.rand.nextInt(100) + 1;
                        if(i<25&&i>=1){
                            num = 0;
                        }else if(i<50&&i>=25){
                            num = 1;
                        }else if(i<75&&i>=50){
                            num = 2;
                        }else if(i<100&&i>=75){
                            num = 2;
                        }
                    }
                    mapTileNumber[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (mapTileNumber);
    }

    public void getTileImages() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/backgrounds/brickFlor/flor1.png")));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/backgrounds/brickFlor/flor2.png")));
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/backgrounds/brickFlor/flor3.png")));
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/backgrounds/brickFlor/flor4.png")));
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/backgrounds/wall.png")));
            tile[4].collision = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNumber[worldCol][worldRow];
            int worldX = worldCol * gp.finalSize;
            int worldY = worldRow * gp.finalSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            if (worldX + gp.finalSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.finalSize< gp.player.worldX + gp.player.screenX &&
                    worldY + gp.finalSize> gp.player.worldY - gp.player.screenY &&
                    worldY - gp.finalSize< gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.finalSize, gp.finalSize, null);
            }
            worldCol++;
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
