package org.entity;

import org.main.MainJPanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class npcRedMage extends Entity {
    public npcRedMage(MainJPanel gp) {
        super(gp);
    }
    public void getPlayerImages() {
        up1 = setup("/player/opponent/redMage/up1");
        up2 = setup("/player/opponent/redMage/up2");
        down1 = setup("/player/opponent/redMage/down1");
        down2 = setup("/player/opponent/redMage/down2");
        left1 = setup("/player/opponent/redMage/left1");
        left2 = setup("/player/opponent/redMage/left2");
        right1 = setup("/player/opponent/redMage/right1");
        right2 = setup("/player/opponent/redMage/right2");

        imgDef1 = setup("/player/opponent/redMage/defaultDown");
        imgDef2 = setup("/player/opponent/redMage/defaultRight");
        imgDef3 = setup("/player/opponent/redMage/defaultLeft");
        imgDef4 = setup("/player/opponent/redMage/defaultUp");
    }
}
