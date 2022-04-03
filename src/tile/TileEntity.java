package tile;

import entity.Entity;

import java.awt.image.BufferedImage;

public abstract class TileEntity extends Entity {
    TileManager manager;
    public BufferedImage texture;
    public int tileX, tileY;


    public TileEntity(float x, float y, float width, float height, int tileX, int tileY, TileManager manager, BufferedImage texture) {
        super(x, y, width, height, manager);
        this.manager = manager;
        this.texture = texture;
        this.tileX = tileX;
        this.tileY = tileY;
    }
}
