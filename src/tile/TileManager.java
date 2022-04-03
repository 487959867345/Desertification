package tile;

import entity.Entity;
import entity.EntityManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class TileManager extends EntityManager {
    public static int tileHeight = 64;
    public static int tileWidth = 64;

    TileEntity[][] tileEntities;
    int sizeX, sizeY;

    public TileManager(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        tileEntities = new TileEntity[sizeX][sizeX];
    }

    public void tick(float deltaTime) {
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                TileEntity ent = tileEntities[x][y];

                if (ent != null) {
                    ent.tick(deltaTime);
                }
            }
        }
    }

    public void render(Graphics2D graphics, float deltaTime) {
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                TileEntity ent = tileEntities[x][y];

                if (ent != null) {
                    ent.render(graphics, deltaTime);
                }
            }
        }
    }

    public ArrayList<TileEntity> calculateCollision(Entity ent) {
        ArrayList<TileEntity> ents = new ArrayList();
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                TileEntity tileEnt = tileEntities[x][y];

                if (ent.getX() < tileEnt.getEndWidth() && ent.getEndWidth() > tileEnt.getX()) {
                    if (ent.getY() < tileEnt.getEndHeight() && ent.getEndHeight() > tileEnt.getY()) {
                        ents.add(tileEnt);
                    }
                }
            }
        }
        return ents;
    }

    public TileEntity getTile(int x, int y) {
        return tileEntities[x][y];
    }

    public void setTile(TileEntity tile, int x, int y) {
        tile.y = y * tileHeight;
        tile.x = x * tileWidth;
        tile.transform.setToTranslation(x * tileWidth, y * tileHeight);
        tile.tileX = x;
        tile.tileY = y;
        tileEntities[x][y] = tile;
    }
}
