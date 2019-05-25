package main;

import main.controls.Keyboard;
import main.controls.Mouse;
import main.game.Juego;
import main.window.Ventana;

public class Global {
    public static int window_width = 400;
    public static int window_height = window_width * 3 / 4;
    public static final byte FPS_OBJETIVO = 60;

    public static final Mouse MOUSE = new Mouse();
    public static final Keyboard KEYBOARD = new Keyboard();
    public static final Ventana VENTANA = new Ventana(window_width, window_height);
    public static final Juego JUEGO = new Juego();
}
