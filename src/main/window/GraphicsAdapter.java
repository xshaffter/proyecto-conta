package main.window;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GraphicsAdapter {
    private Graphics g;

    public GraphicsAdapter(final Graphics g) {
        this.g = g;
    }


    public void drawImage(final BufferedImage image, final int x, final int y) {
        g.drawImage(image, x, y, null);
    }

    public void borderedRect(final Color borderColor, final Color backgroundColor, final int x, final int y, final int width, final int height) {
        g.setColor(backgroundColor);
        g.fillRect(x, y, width, height);
        g.setColor(borderColor);
        g.drawRect(x, y, width, height);
    }

    public void fillRect(Color color, int x, int y, int width, int height) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    public void dispose() {
        g.dispose();
    }

    public void drawCursor(final int x, final int y, final int y2) {

        g.setColor(Color.BLACK);
        g.drawLine(x, y, x, y2);
    }

    public void drawText(final Color color, final String text, final int x, final int y) {
        g.setColor(color);
        g.drawString(text, x, y);
    }

    public Graphics graphics() {
        return g;
    }

    public int getStringHeight(String text) {
        FontMetrics metrics = g.getFontMetrics();
        return (int) metrics.getLineMetrics(text, g).getHeight();
    }

    public int stringWidth(String text) {
        FontMetrics metrics = g.getFontMetrics();
        return metrics.stringWidth(text);
    }
}
