import tile.TileEntity;
import tile.TileProfile;
import tile.TileProfileInterface;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tiles {
    static BufferedImage  grassTexture;
    static BufferedImage sandTexture;
    static BufferedImage waterTexture;

    static {
        try {
            sandTexture = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("textures/tile/sand.png"));
            waterTexture = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("textures/tile/water.png"));
            grassTexture = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("textures/tile/grass.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static TileProfile grassTile;
    static TileProfile sandTile;
    static TileProfile waterTile;
    public static void registerTiles() {
        grassTile = new TileProfile(grassTexture, null);
        TileProfileInterface grassInterface = new TileProfileInterface() {
            @Override
            public TileEntity toTileEntity() {
                return new GrassTile(0, 0, 0, 0, grassTile.getTexture().getWidth(), grassTile.getTexture().getHeight(), null, grassTile.getTexture());
            }
        };
        grassTile.setTileProfileInterface(grassInterface);

        sandTile = new TileProfile(sandTexture, null);
        TileProfileInterface sandInterface = new TileProfileInterface() {
            @Override
            public TileEntity toTileEntity() {
                return new SandTile(0,0,0,0, sandTile.getTexture().getWidth(), sandTile.getTexture().getHeight(), null, sandTile.getTexture());
            }
        };
        sandTile.setTileProfileInterface(sandInterface);

        waterTile = new TileProfile(waterTexture, null);

        TileProfileInterface waterInterface = new TileProfileInterface() {
            @Override
            public TileEntity toTileEntity() {
                return new WaterTile(0,0,0,0,waterTile.getTexture().getWidth(), waterTile.getTexture().getHeight(), null, waterTile.getTexture());
            }
        };
        waterTile.setTileProfileInterface(waterInterface);

    }
}
