package org.objects;

import javax.imageio.ImageIO;

public class ObjectChest extends SuperObject{
    public ObjectChest() {
        name = "Chest";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
