package com.row666.game.gfx;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Assets {
    private static final int spriteSize = 16;
    private static final int sheetSize = spriteSize * 20;
    private ArrayList<BufferedImage> entitySprites = new ArrayList<>();
    private ArrayList<BufferedImage> tiles = new ArrayList<>();
    private static Assets assets = new Assets();
    
    
    private Assets() {
        SpriteSheet entitySprites = new SpriteSheet(ImageLoader.loadImage("/assets/textures/sheet_entities.png"));
        SpriteSheet tiles = new SpriteSheet(ImageLoader.loadImage("/assets/textures/sheet_tiles.png"));
        for(int y = 0; y < sheetSize / spriteSize; y++){
            for(int x = 0; x < sheetSize / spriteSize; x++) {
                this.entitySprites.add(entitySprites.crop(x * spriteSize, y * spriteSize));
                this.tiles.add(tiles.crop(x * spriteSize, y * spriteSize));
            }
        }
    }
    
    public static Assets getInstance() {
        return assets;
    }
    
    public ArrayList<BufferedImage> getEntitySprites() {
        return entitySprites;
    }

    public ArrayList<BufferedImage> getTiles() {
        return tiles;
    }
    
}
