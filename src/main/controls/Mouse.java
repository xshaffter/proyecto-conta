package main.controls;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import javax.swing.*;
import main.window.panels.Lienzo;

public class Mouse extends MouseAdapter {
    private Point posicion;
    private boolean click = false;

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        click = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        click = false;
    }

    public Mouse() {
        posicion = new Point();
    }

    public void actualizar(final Lienzo lienzo) {
        setPosicion(lienzo);
    }

    public Rectangle getPosicion() {
        return new Rectangle(posicion.x, posicion.y, 1, 1);
    }

    private void setPosicion(final Lienzo lienzo) {
        final Point posicion = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(posicion, lienzo);
        this.posicion.setLocation(posicion);
    }

    public boolean isClicked() {
        return click;
    }

    public boolean isHover(final Rectangle box) {
        return new Rectangle(posicion.x, posicion.y, 1, 1).intersects(box);
    }
}
