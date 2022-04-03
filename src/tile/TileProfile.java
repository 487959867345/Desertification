package tile;

import java.awt.image.BufferedImage;

public class TileProfile {
    BufferedImage texture;
    TileProfileInterface tileProfileInterface;

    public TileProfile(BufferedImage texture, TileProfileInterface inter) {
        this.texture = texture;
        this.tileProfileInterface = inter;
    }


    public TileEntity toTileEntity() {
        return tileProfileInterface.toTileEntity();
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public TileProfileInterface getTileProfileInterface() {
        return tileProfileInterface;
    }

    public void setTileProfileInterface(TileProfileInterface tileProfileInterface) {
        this.tileProfileInterface = tileProfileInterface;
    }
}
