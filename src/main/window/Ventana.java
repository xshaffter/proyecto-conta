package main.window;

import java.awt.*;
import javax.swing.*;
import main.window.panels.Lienzo;
import main.window.panels.components.TextField;

public class Ventana extends JFrame {

    private Lienzo lienzo;
    private int aps = 0;
    private int fps = 0;
    private boolean enFuncionamiento = true;

    public Ventana(final int ancho, final int alto) {
        final BorderLayout mainLayout = new BorderLayout();
        this.lienzo = new Lienzo(ancho, alto);

        this.setLayout(mainLayout);

        this.setPreferredSize(new Dimension(ancho, alto));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);

        TextField txtRespuesta = new TextField(null, 15, 0, 0);
        txtRespuesta.setHint("");
        lienzo.add(txtRespuesta);


        this.add(lienzo, "North");
    }

    public void start() {
        Thread dibujo = new Thread(() -> {
            int actualizacionesAcumuladas = 0;
            int framesAcumulados = 0;

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
                    actualizacionesAcumuladas++;
                    delta--;
                }

                lienzo.dibujar();
                framesAcumulados++;

                if (System.nanoTime() - referenciaContador > NS_POR_SEGUNDO) {

                    aps = actualizacionesAcumuladas;
                    fps = framesAcumulados;
                    actualizacionesAcumuladas = 0;
                    framesAcumulados = 0;
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
