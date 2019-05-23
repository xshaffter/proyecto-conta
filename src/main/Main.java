package main;

public class Main {
    public static void main(final String[] args) {
        System.setProperty("sun.java2d.opengl", "True");
        Global.VENTANA.setVisible(true);
        Global.VENTANA.start();
    }
}
