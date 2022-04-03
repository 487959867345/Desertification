import entity.Entity;
import entity.EntityManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TumbleWeedEntity extends Entity {
    static BufferedImage texture;

    static {
        try {
            texture = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("textures/player/tumbleweed.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TumbleWeedEntity(float x, float y, float width, float height, EntityManager manager) {
        super(x, y, width, height, manager);
    }

    @Override
    public void tick(float deltaTime) {
        super.tick(deltaTime);
        x -= 0.5 * deltaTime;
        transform.setToTranslation(x,y);
    }

    @Override
    public void render(Graphics2D graphics, float deltaTime) {
        graphics.drawImage(texture, transform, null);
    }

    @Override
    public void onCollision(Entity ent) {
    }

    @Override
    public boolean hasCollisions() {
        return true;
    }
}
