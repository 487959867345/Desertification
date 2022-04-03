import tile.TileManager;

import java.awt.*;

public class Main {
    static Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

    public static void main(String[] args) {
        GameWindow window = new GameWindow(14 * TileManager.tileWidth, 14 * TileManager.tileHeight, "Desertification");

        Game game = new Game(window);

        game.start();
    }
}
