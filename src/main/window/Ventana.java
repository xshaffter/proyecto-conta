package main.window;

import java.awt.*;
import javax.swing.*;
import kotlin.Unit;
import main.Global;
import main.window.panels.Lienzo;
import main.window.panels.components.BottomMenu;
import main.window.panels.components.TextField;
import main.window.panels.components.layouts.GBorderLayout;

import static main.Global.JUEGO;


public class Ventana extends JFrame {

    private Lienzo lienzo;
    private boolean enFuncionamiento = true;

    public Ventana(final int ancho, final int alto) {
        final BorderLayout mainLayout = new BorderLayout();
        this.lienzo = new Lienzo(ancho, alto);

        this.setLayout(mainLayout);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        TextField txtRespuesta = new TextField(null, 1, 0, alto - 128);
        GBorderLayout borderLayout = new GBorderLayout(0, 0, Global.window_width, Global.window_height);
        BottomMenu menu = new BottomMenu(Global.window_width, 128);

        txtRespuesta.setOnAction(() -> {
            if (!txtRespuesta.getText().isEmpty()) {
                char letra = txtRespuesta.getText().toUpperCase().charAt(0);
                if (JUEGO.getLetras().contains(letra)) {
                    JUEGO.getLetras().set(JUEGO.getLetras().indexOf(letra), '_');
                }
            }
            return Unit.INSTANCE;
        });
        this.add(lienzo, BorderLayout.CENTER);
        borderLayout.setBottom(menu);
        menu.add(txtRespuesta);
        lienzo.add(borderLayout);
        this.pack();
        this.setLocationRelativeTo(null);
        txtRespuesta.setFocused(true);
    }

    public void start() {
        Thread dibujo = new Thread(() -> {
            final int NS_POR_SEGUNDO = 1000000000;
            final int APS_OBJETIVO = 60;
            final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO;

            long referenciaActualizacion = System.nanoTime();
            long referenciaContador = System.nanoTime();

            double tiempoTranscurrido;
            double delta = 0;

            while (enFuncionamiento) {
                final long inicioBucle = System.nanoTime();

                tiempoTranscurrido = inicioBucle - referenciaActualizacion;
                referenciaActualizacion = inicioBucle;

                delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;

                while (delta >= 1) {
                    actualizar();
                    delta--;
                }

                lienzo.dibujar();

                if (System.nanoTime() - referenciaContador > NS_POR_SEGUNDO) {

                    referenciaContador = System.nanoTime();
                }
            }
        }, "dibujar");
        dibujo.start();
    }

    public Lienzo getLienzo() {
        return lienzo;
    }

    private void actualizar() {
        lienzo.actualizar();
    }
}
