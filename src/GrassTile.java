import tile.TileEntity;
import tile.TileManager;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class GrassTile extends TileEntity {




    public GrassTile(float x, float y, float width, float height, int tileX, int tileY, TileManager manager, BufferedImage texture) {
        super(x, y, width, height, tileX, tileY, manager, texture);
        transform.setToTranslation(x, y);
    }

    @Override
    public void tick(float deltaTime) {
        transform.setToTranslation(x, y);
    }

    @Override
    public void render(Graphics2D graphics, float deltaTime) {
        graphics.drawImage(texture, transform, null);
    }
}
