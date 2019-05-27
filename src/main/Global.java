package main;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.game.Juego;
import main.game.Palabra;
import main.window.Ventana;

public class Global {
    public static int window_width = 400;
    public static int window_height = window_width * 3 / 4;

    public static final Random rnd = new Random();
    public static Ventana VENTANA;
    public static final ArrayList<Palabra> palabras = new ArrayList<>();
    public static final Juego JUEGO = new Juego();
    public static final Border BORDER = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
    public static final Font FONT = new Font("Console", 12);
}
