package entity;

import java.awt.*;

public interface EntityInterface {
    void tick (float deltaTime);

    void render(Graphics2D graphics, float deltaTime);
}
