package org.objects;

import javax.imageio.ImageIO;

public class ObjectBook extends SuperObject {
    public ObjectBook() {
        name = "Book";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/mageBook.png"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
