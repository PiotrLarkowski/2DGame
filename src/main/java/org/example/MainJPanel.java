package org.example;

import javax.swing.*;
import java.awt.*;

public class MainJPanel extends JPanel {
    final int originalTileSize = 16;
    final int scale = 3;

    final int finalSize = originalTileSize * scale;
    final int maxScreenCol =16;
    final int maxScreenRow = 12;
    final int screenWidth = finalSize * maxScreenCol;
    final int screenHeight = finalSize * maxScreenRow;

    public MainJPanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }
}
