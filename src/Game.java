import entity.Entity;
import entity.EntityManager;
import key.KeyListener;
import key.KeyListenerCallback;
import tile.TileEntity;
import tile.TileManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Game implements Runnable{
    Thread thread;
    GameWindow window;
    boolean running = false;

    Canvas canvas;
    Graphics graphics;
    BufferStrategy bufferStrategy;
    long lastTime = System.currentTimeMillis();
    long deltaTime = lastTime;
    long desertTimeMillis = 2000;
    long lastDesertTick = lastTime;
    long lastChargeTick = lastTime;
    long lastSpawnTick = lastTime;
    Random random = new Random();


    BufferedImage heartTexture;

    {
        try {
            heartTexture = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("textures/player/heart.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    TileManager tileManager;
    EntityManager entityManager;
    KeyListener keyListener;
    int charge = 100;
    PlayerEntity player;
    public Game(GameWindow window) {
        this.window = window;

        canvas = window.getCanvas();
    }

    private void resetTiles() {
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 14; j++) {
                tileManager.setTile(Tiles.grassTile.toTileEntity(), i, j);
            }
        }
    }


    private void init() {
        Tiles.registerTiles();
        keyListener = new KeyListener();
        tileManager = new TileManager(14,14);
        entityManager = new EntityManager();
        resetTiles();
        PlayerEntity player = new PlayerEntity(10,10, 64, 64, entityManager);
        this.player =  player;
        this.player.setLives(3);

        player.speed = 0.5f;

        entityManager.getEntityLinkedList().add(player);

        entityManager.getEntityLinkedList().add(new Entity(0, -10, canvas.getWidth(),1, entityManager) {
            @Override
            public boolean hasCollisions() {
                return true;
            }
        });
        entityManager.getEntityLinkedList().add(new Entity(-20,0, 1, canvas.getHeight(), entityManager) {
            @Override
            public boolean hasCollisions() {
                return true;
            }
        });
        entityManager.getEntityLinkedList().add(new Entity(0, canvas.getHeight(), canvas.getWidth(),1, entityManager) {
            @Override
            public boolean hasCollisions() {
                return true;
            }
        });
        entityManager.getEntityLinkedList().add(new Entity(canvas.getWidth(), 0, 1, canvas.getHeight(), entityManager) {
            @Override
            public boolean hasCollisions() {
                return true;
            }
        });

        canvas.addKeyListener(keyListener);
        keyListener.getCallbacks().add(new KeyListenerCallback(KeyEvent.VK_W) {
            @Override
            public void keyPressed() {
                if (player.movingUp != true) {
                    player.movingUp = true;
                    player.movingDown = false;
                }
            }

            @Override
            public void keyReleased() {
                player.movingUp = false;
            }
        });
        keyListener.getCallbacks().add(new KeyListenerCallback(KeyEvent.VK_S) {
            @Override
            public void keyPressed() {
                if (player.movingDown != true) {
                    player.movingUp = false;
                    player.movingDown = true;
                }
            }

            @Override
            public void keyReleased() {
                player.movingDown = false;
            }
        });
        keyListener.getCallbacks().add(new KeyListenerCallback(KeyEvent.VK_A) {
            @Override
            public void keyPressed() {
                if (player.movingLeft != true) {
                    player.movingRight = false;
                    player.movingLeft = true;
                }
            }

            @Override
            public void keyReleased() {
                player.movingLeft = false;
            }
        });
        keyListener.getCallbacks().add(new KeyListenerCallback(KeyEvent.VK_D) {
            @Override
            public void keyPressed() {
                if (player.movingRight != true) {
                    player.movingRight = true;
                    player.movingLeft = false;
                }
            }

            @Override
            public void keyReleased() {
                player.movingRight = false;
            }
        });
        keyListener.getCallbacks().add(new KeyListenerCallback(KeyEvent.VK_SPACE) {
            @Override
            public void keyReleased() {
                if (charge >= 100) {
                    ArrayList<TileEntity> ents = tileManager.calculateCollision(player);
                    for (TileEntity ent: ents) {
                        if (ent instanceof SandTile) {
                            tileManager.setTile(Tiles.grassTile.toTileEntity(), ent.tileX, ent.tileY);
                            desertTimeMillis += 50;
                        }else if (ent instanceof WaterTile) {
                            resetTiles();
                            desertTimeMillis = 2000;
                        }
                    }
                    charge = 0;
                }
            }
        });
    }

    private void tick() {
        deltaTime = System.currentTimeMillis() - lastTime;

        if (lastTime - lastChargeTick >= 30 && charge <= 100) {
            charge += 1;
            lastChargeTick = lastTime;
        }

        if (lastTime - lastDesertTick >= desertTimeMillis) {
            int num = random.nextInt(1,10);
            System.out.println(num);
            if (num == 9) {
                tileManager.setTile(Tiles.waterTile.toTileEntity(), random.nextInt(0,14), random.nextInt(0,14));
            }else {
                tileManager.setTile(Tiles.sandTile.toTileEntity(), random.nextInt(0,14), random.nextInt(0,14));
            }
            lastDesertTick = lastTime;
            desertTimeMillis -= 50;
            if (desertTimeMillis <= 0) {
                System.exit(0);
            }
        }

        if (lastTime - lastSpawnTick >= 1500) {
            int spawn = random.nextInt(50, canvas.getHeight() - 50);
            entityManager.scheduleAdd(new TumbleWeedEntity(canvas.getWidth() - 30, spawn,20,20, entityManager));
            lastSpawnTick = lastTime;
        }

        //System.out.println(deltaTime);
        tileManager.tick(deltaTime);
        entityManager.tick(deltaTime);
        lastTime = System.currentTimeMillis();
    }

    private void render() {
        bufferStrategy = canvas.getBufferStrategy();

        if (bufferStrategy == null) {
            canvas.createBufferStrategy(3);
            return;
        }

        graphics = bufferStrategy.getDrawGraphics();

        graphics.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        //draw here
        Graphics2D graphics2D = (Graphics2D) graphics;

        tileManager.render(graphics2D, deltaTime);
        entityManager.render(graphics2D, deltaTime);
        graphics2D.setColor(Color.black);
        graphics2D.fillRect(5,5,205,50);
        graphics2D.setColor(Color.GREEN);
        graphics2D.fillRect(7,7, charge * 2,45);

        for (int i = 0; i < player.getLives() + 1; i++) {
            graphics2D.drawImage(heartTexture, canvas.getWidth() - (64 * i), 0, null);
        }

        // end draw
        graphics.dispose();
        bufferStrategy.show();
    }

    private void onExit() {

    }

    @Override
    public void run() {
        init();
        while (running) {
            tick();
            render();
        }
        onExit();
    }

    public void stop() {
        if (!running)
            return;
        try {
            running = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        if (running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }
}
