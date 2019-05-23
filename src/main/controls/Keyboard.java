package main.controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
    private boolean isPressed = false;
    private int tecla = ' ';

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        isPressed = true;
        if (e.getKeyChar() != 8) {
            tecla = e.getKeyChar();
        } else {
            tecla = e.getKeyCode();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        isPressed = false;
    }

    public boolean isPressed() {
        return isPressed;
    }

    public void setPressed(boolean pressed) {
        isPressed = pressed;
        tecla = 0;
    }

    public int getTecla() {
        return tecla;
    }
}
