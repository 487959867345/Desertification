import entity.Entity;
import entity.EntityManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PlayerEntity extends Entity {
    static BufferedImage texture;

    static {
        try {
            texture = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("textures/player/player.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    float speed = 0;
    boolean movingUp = false;
    boolean movingDown = false;
    boolean movingLeft = false;
    boolean movingRight = false;
    float lastX = 0;
    float lastY = 0;
    int lives = 0;
    AffineTransform transform;

    public PlayerEntity(float x, float  y, float width, float height, EntityManager manager) {
        super(0, 0, width, height, manager);
        transform = new AffineTransform();
        this.x = x;
        this.y = y;
        transform.setToTranslation(x, y);
    }


    @Override
    public void tick(float deltaTime) {
        lastX = x;
        lastY = y;
        transform.setToTranslation(x, y);

        if (movingUp) {
            y += -(speed * deltaTime);
        } else if (movingDown) {
            y += (speed * deltaTime);
        }

        if (movingLeft) {
            x += -(speed * deltaTime);
        } else if (movingRight) {
            x += (speed * deltaTime);
        }

        if (lives == 0) {
            System.exit(1);
        }
    }

    @Override
    public void render(Graphics2D graphics, float deltaTime) {
        graphics.drawImage(texture, transform, null);
    }

    @Override
    public void onCollision(Entity collidee) {
        x = lastX;
        y = lastY;

        if (collidee instanceof TumbleWeedEntity) {
            lives -= 1;
            manager.scheduleRemoval(collidee);
        }
    }

    @Override
    public boolean hasCollisions() {
        return true;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int live) {
        this.lives = live;
    }
}
