package key;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class KeyListenerCallback {
    int key;

    public KeyListenerCallback(int key) {
        this.key = key;
    }


    public void keyTyped(KeyEvent e) {

    }


    public void keyPressed() {

    }


    public void keyReleased() {

    }
}
