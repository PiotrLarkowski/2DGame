package org.objects;

import javax.imageio.ImageIO;

public class ObjectGate extends SuperObject{
    public ObjectGate() {
        name = "Chest";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/gate.png"));
        }catch(Exception e){
            e.printStackTrace();
        }
        collision = true;
    }
}
