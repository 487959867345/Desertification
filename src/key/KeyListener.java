package key;

import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class KeyListener implements java.awt.event.KeyListener {
    LinkedList<KeyListenerCallback> callbacks = new LinkedList<>();
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        for (KeyListenerCallback callback: callbacks) {
            if (callback.key == e.getKeyCode()) {
                callback.keyPressed();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        for (KeyListenerCallback callback: callbacks) {
            if (callback.key == e.getKeyCode()) {
                callback.keyReleased();
            }
        }
    }

    public LinkedList<KeyListenerCallback> getCallbacks() {
        return callbacks;
    }
}
