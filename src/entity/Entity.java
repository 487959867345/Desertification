package entity;

import java.awt.*;
import java.awt.geom.AffineTransform;

public abstract class Entity implements EntityInterface {
    public float x;
    public float y;
    public float width;
    public float height;
    public AffineTransform transform = new AffineTransform();
    public EntityManager manager;
    public Entity(float x, float y, float width, float height, EntityManager manager) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.manager = manager;
    }


    @Override
    public void tick(float deltaTime) {

    }

    @Override
    public void render(Graphics2D graphics, float deltaTime) {

    }

    public void onCollision(Entity collidee) {
    }

    public boolean hasCollisions() {
        return false;
    }

    public float getEndWidth() {
       return  x + width;
    }

    public float getEndHeight() {
        return  y + height;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
