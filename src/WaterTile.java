import tile.TileEntity;
import tile.TileManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WaterTile extends TileEntity {
    public WaterTile(float x, float y, float width, float height, int tileX, int tileY, TileManager manager, BufferedImage texture) {
        super(x, y, width, height, tileX, tileY, manager, texture);
    }

    @Override
    public void tick(float deltaTime) {
        super.tick(deltaTime);
    }

    @Override
    public void render(Graphics2D graphics, float deltaTime) {
        graphics.drawImage(texture, (int) x, (int) y, null);
    }
}
