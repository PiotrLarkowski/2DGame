package org.objects;

import javax.imageio.ImageIO;

public class ObjectTurnstile extends SuperObject{
    public ObjectTurnstile() {
        name = "Turnstile";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/turnstileForGate.png"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
