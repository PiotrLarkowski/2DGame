package org.objects;

import javax.imageio.ImageIO;

public class ObjectTurnstile extends SuperObject{
    public ObjectTurnstile(int connectGate) {
        name = "Turnstile";
        this.connectGateTurnstile = connectGate;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/turnstileForGate.png"));
        }catch(Exception e){
            e.printStackTrace();
        }
        collision = true;
    }
}
