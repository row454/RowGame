package main.com.row666.game.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {
    
    private BufferedImage sheet;
    
    public SpriteSheet(BufferedImage sheet) {
        this.sheet = sheet;
    }
    
    public BufferedImage crop(int vrow, int hrow) {
            return sheet.getSubimage(vrow, hrow, 16, 16);
    }
    
}
