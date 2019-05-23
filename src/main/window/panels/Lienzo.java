package main.window.panels;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import main.Global;
import main.window.GraphicsAdapter;
import main.window.panels.components.GComponent;

public class Lienzo extends Canvas {
    private final ArrayList<GComponent> components = new ArrayList<>();
    private int ancho, alto;

    public Lienzo(final int ancho, final int alto) {
        setSize(ancho, alto);
        this.ancho = ancho;
        this.alto = alto;

        this.addMouseListener(Global.MOUSE);
        this.addKeyListener(Global.KEYBOARD);
    }

    public void add(final GComponent component) {
        components.add(component);
    }

    public void dibujar() {
        final BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        final GraphicsAdapter g = new GraphicsAdapter(bs.getDrawGraphics());

        g.fillRect(Color.cyan, 0, 0, ancho, alto);

        for (GComponent component : components) {
            component.dibujar(g);
        }

        g.dispose();
        bs.show();
    }

    public ArrayList<GComponent> getComponents() {
        return components;
    }

    public void actualizar() {
        Global.MOUSE.actualizar(this);

        for (GComponent component : components) {
            component.actualizarBase();
        }
    }
}
