package main;

import javafx.application.Application;
import javafx.stage.Stage;
import main.window.Ventana;

import static main.Global.window_height;
import static main.Global.window_width;

public class Main extends Application {
    public static void main(final String[] args) {
        Utilities.loadPalabras();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Global.VENTANA = new Ventana(window_width, window_height);
        Global.VENTANA.show();
        Global.JUEGO.reset();
    }
}
