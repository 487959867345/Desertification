package entity;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

public class EntityManager {
    LinkedList<Entity> entityLinkedList = new LinkedList<>();
    LinkedList<Entity> removeList = new LinkedList<>();
    LinkedList<Entity> addList = new LinkedList<>();

    public void scheduleRemoval(Entity entity) {
        removeList.add(entity);
    }
    public void scheduleAdd(Entity entity) {
        addList.add(entity);
    }


    public void tick(float deltaTime) {
        for (Entity e: removeList) {
            entityLinkedList.remove(e);
        }

        Iterator<Entity> addEnts = addList.iterator();

        while (addEnts.hasNext()) {
            Entity ent = addEnts.next();
            entityLinkedList.add(ent);
            addEnts.remove();
        }


        Iterator<Entity> ents = entityLinkedList.iterator();

        while (ents.hasNext()) {
            Entity ent = ents.next();
            ent.tick(deltaTime);
            calculateCollision(ent);
        }
    }

    private void calculateCollision(Entity ent) {
        Iterator<Entity> ents = entityLinkedList.iterator();
        while (ents.hasNext()) {
            Entity collidee = ents.next();
            if (ent.equals(collidee) || !ent.hasCollisions() || !collidee.hasCollisions()) {
                continue;
            }

            if (ent.getX() < collidee.getEndWidth() && ent.getEndWidth() > collidee.getX()) {
                if (ent.getY() < collidee.getEndHeight() && ent.getEndHeight() > collidee.getY()) {
                    ent.onCollision(collidee);
                }
            }
        }
    }

    public void render(Graphics2D graphics, float deltaTime) {
        Iterator<Entity> ents = entityLinkedList.iterator();

        while (ents.hasNext()) {
            Entity ent = ents.next();
            ent.render(graphics, deltaTime);
        }
    }

    public LinkedList<Entity> getEntityLinkedList() {
        return entityLinkedList;
    }
}
