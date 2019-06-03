package main;

import javafx.application.Application;
import javafx.stage.Stage;
import main.config.DBDriver;
import main.game.TablaResultados;
import main.window.Ventana;

import static main.Global.window_height;
import static main.Global.window_width;

public class Main extends Application {
    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Global.DRIVER = new DBDriver();
        Utilities.loadPalabras();
        Global.VENTANA = new Ventana(window_width, window_height);
        new TablaResultados();
//        Global.VENTANA.show();
        Global.JUEGO.reset();

        Global.VENTANA.setOnHiding((e) -> System.exit(0));
    }
}