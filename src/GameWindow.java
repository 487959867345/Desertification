import javax.swing.*;
import java.awt.*;


public class GameWindow {
    Canvas canvas;
    JFrame frame;
    int width, height;
    Dimension dimension;
    String title;

    public GameWindow(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;

        dimension = new Dimension(width, height);

        construct();
    }

    private void construct() {
        frame = new JFrame();
        frame.setTitle(title);
        frame.setSize(dimension);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.requestFocus();
        frame.setResizable(true);
        frame.setMaximumSize(dimension);

        canvas = new Canvas();
        canvas.setSize(dimension);

        frame.add(canvas);

        frame.pack();
        frame.setVisible(true);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
