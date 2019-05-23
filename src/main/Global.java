package main;

import main.controls.Keyboard;
import main.controls.Mouse;
import main.game.Juego;
import main.window.Ventana;

public class Global {
    public static final byte FPS_OBJETIVO = 60;
    public static final Mouse MOUSE = new Mouse();
    public static final Keyboard KEYBOARD = new Keyboard();
    public static final Ventana VENTANA = new Ventana(400, 300);
    public static final Juego JUEGO = new Juego();
}
