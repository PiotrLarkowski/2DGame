package org.objects;

import javax.imageio.ImageIO;
import java.util.Arrays;

public class ObjectBook extends SuperObject {
    public ObjectBook(String magicSpellName) {
        spellName = magicSpellName;
        name = "Book";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/mageBook.png"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
