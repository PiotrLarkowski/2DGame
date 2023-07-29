package org.background;

import org.main.MainJPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    MainJPanel gp;
    public Tile[] tile;

    public int mapTileNumber[][];

    public TileManager(MainJPanel mainJPanel) {
        this.gp = mainJPanel;
        tile = new Tile[30];
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
        for (int i = 0; i < gp.maxWorldCol; i++) {
            for (int j = 0; j < gp.maxWorldRow; j++) {
                if(mapTileNumber[i][j]==1){
                    if(i==0 && mapTileNumber[i+1][j]==1 && mapTileNumber[i][j+1] == 1){

                    }
                }
            }

        }
        return (mapTileNumber);
    }

    public void getTileImages() {
        String[] paths= {
                "/backgrounds/brickFlor/flor1",
                "/backgrounds/brickFlor/flor2",
                "/backgrounds/brickFlor/flor3",
                "/backgrounds/brickFlor/flor4",
                "/backgrounds/walls/center",
                "/backgrounds/walls/downleftup",
                "/backgrounds/walls/downright",
                "/backgrounds/walls/downrightup",
                "/backgrounds/walls/downup",
                "/backgrounds/walls/leftdown",
                "/backgrounds/walls/leftdownright",
                "/backgrounds/walls/leftdownrightup",
                "/backgrounds/walls/leftright",
                "/backgrounds/walls/leftup",
                "/backgrounds/walls/upright",
                "/backgrounds/walls/uprightleft"
        };
        boolean[] collisionArray =
                {false,false,false,false,true,true,true,true,true,true,true,true,true,true,true,true};
        for (int i = 0; i < paths.length; i++) {
            loadResources(i,paths[i],collisionArray[i]);
        }
    }

    private void loadResources(int i,String path, boolean collision){
        try {
            tile[i]=new Tile();
            tile[i].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + ".png")));
            tile[i].collision = collision;
        } catch (IOException e) {
            throw new RuntimeException(e);
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
