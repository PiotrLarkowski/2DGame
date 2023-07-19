package org.objects;

import javax.imageio.ImageIO;

public class ObjectGate extends SuperObject{
    public ObjectGate(int connectTurnstile) {
        name = "Gate";
        this.connectGateTurnstile = connectTurnstile;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/gate.png"));
        }catch(Exception e){
            e.printStackTrace();
        }
        collision = true;
    }
}
