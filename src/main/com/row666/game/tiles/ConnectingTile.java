package main.com.row666.game.tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ConnectingTile extends Tile {
    
    
    private BufferedImage[] subTextures;
    private static final GraphicsConfiguration graphicsConfiguration = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
    
    /**
     *  @param textures
     *      Accepts an array of 5 elements with each image being as follows:
     *      1st has no edges,
     *      2nd has vertical edges,
     *      3rd has horizontal edges,
     *      4th has corners,
     *      and the 5th has all edges.
     *
     */
    public ConnectingTile(BufferedImage[] textures, int id) {
        this(textures, id, false);

    }
    public ConnectingTile(BufferedImage[] textures, int id, boolean solid) {
        super(textures[0], id, solid);
        if(textures.length != 5) {
            throw new IllegalArgumentException();
        }
        subTextures = new BufferedImage[20];
        for(int i = 0; i < subTextures.length; i++) {
            subTextures[i] = textures[i%5].getSubimage(i/5%2*(TEXTURE_WIDTH/2),i/10%2*(TEXTURE_HEIGHT/2), TEXTURE_WIDTH/2, TEXTURE_HEIGHT/2);
        }
        
    }
    @Override
    public void render(Graphics g, int x, int y, TileManager tileManager) {
        boolean[] neighbours = new boolean[8];
        int offset = 0;
        for(int i = 0; i<3; i++) {
            for(int j = 0; j<3; j++) {
                if(i==1&j==1) {
                    offset++;
                    continue;
                }
                neighbours[i*3+j-offset] = tileManager.getTile(x+j-1, y+i-1).getId() == this.getId();
            }
        }
        // 0 0 0
        // 0   1
        // 0 1 0
        
        BufferedImage texture = graphicsConfiguration.createCompatibleImage(TEXTURE_WIDTH, TEXTURE_HEIGHT);
        Graphics g2 = texture.createGraphics();
        
        
        if(neighbours[1] & neighbours[3]) {
            if(neighbours[0]) {
                g2.drawImage(subTextures[0], 0, 0, TEXTURE_WIDTH/2, TEXTURE_HEIGHT/2, null);
            } else {
                g2.drawImage(subTextures[3], 0, 0, TEXTURE_WIDTH/2, TEXTURE_HEIGHT/2, null);
            }
        } else if(neighbours[1]) {
            g2.drawImage(subTextures[1], 0, 0, TEXTURE_WIDTH/2, TEXTURE_HEIGHT/2, null);
        } else if(neighbours[3]) {
            g2.drawImage(subTextures[2], 0, 0, TEXTURE_WIDTH/2, TEXTURE_HEIGHT/2, null);
        } else {
            g2.drawImage(subTextures[4], 0, 0, TEXTURE_WIDTH/2, TEXTURE_HEIGHT/2, null);
        }
        // !1, 4
        if(neighbours[1] & neighbours[4]) {
            if(neighbours[2]) {
                g2.drawImage(subTextures[5], 8, 0, TEXTURE_WIDTH/2, TEXTURE_HEIGHT/2, null);
            } else {
                g2.drawImage(subTextures[8], 8, 0, TEXTURE_WIDTH/2, TEXTURE_HEIGHT/2, null);
            }
        } else if(neighbours[1]) {
            g2.drawImage(subTextures[6], 8, 0, TEXTURE_WIDTH/2, TEXTURE_HEIGHT/2, null);
        } else if(neighbours[4]) {
            g2.drawImage(subTextures[7], 8, 0, TEXTURE_WIDTH/2, TEXTURE_HEIGHT/2, null);
        } else {
            g2.drawImage(subTextures[9], 8, 0, TEXTURE_WIDTH/2, TEXTURE_HEIGHT/2, null);
        }
        
        if(neighbours[6] & neighbours[3]) {
            if(neighbours[5]) {
                g2.drawImage(subTextures[10], 0, 8, TEXTURE_WIDTH/2, TEXTURE_HEIGHT/2, null);
            } else {
                g2.drawImage(subTextures[13], 0, 8, TEXTURE_WIDTH/2, TEXTURE_HEIGHT/2, null);
            }
        } else if(neighbours[6]) {
            g2.drawImage(subTextures[11], 0, 8, TEXTURE_WIDTH/2, TEXTURE_HEIGHT/2, null);
        } else if(neighbours[3]) {
            g2.drawImage(subTextures[12], 0, 8, TEXTURE_WIDTH/2, TEXTURE_HEIGHT/2, null);
        } else {
            g2.drawImage(subTextures[14], 0, 8, TEXTURE_WIDTH/2, TEXTURE_HEIGHT/2, null);
        }
        
        if(neighbours[6] & neighbours[4]) {
            if(neighbours[7]) {
                g2.drawImage(subTextures[15], 8, 8, TEXTURE_WIDTH/2, TEXTURE_HEIGHT/2, null);
            } else {
                g2.drawImage(subTextures[18], 8, 8, TEXTURE_WIDTH/2, TEXTURE_HEIGHT/2, null);
            }
        } else if(neighbours[6]) {
            g2.drawImage(subTextures[16], 8, 8, TEXTURE_WIDTH/2, TEXTURE_HEIGHT/2, null);
        } else if(neighbours[4]) {
            g2.drawImage(subTextures[17], 8, 8, TEXTURE_WIDTH/2, TEXTURE_HEIGHT/2, null);
        } else {
            g2.drawImage(subTextures[19], 8, 8, TEXTURE_WIDTH/2, TEXTURE_HEIGHT/2, null);
        }
        
        
        g.drawImage(texture, (int) (x * TILE_WIDTH - tileManager.getRoom().getState().getCamera().getXOffset()), (int) (y * TILE_HEIGHT - tileManager.getRoom().getState().getCamera().getYOffset()), TILE_WIDTH, TILE_HEIGHT, null);
    }
}
