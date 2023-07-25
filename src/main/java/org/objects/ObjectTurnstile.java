package org.objects;

import javax.imageio.ImageIO;
import java.util.Objects;

public class ObjectTurnstile extends SuperObject{
    public ObjectTurnstile(int connectGate) {
        name = "Turnstile";
        this.connectGateTurnstile = connectGate;
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/turnstileForGate.png")));
        }catch(Exception e){
            e.printStackTrace();
        }
        collision = true;
    }
}
