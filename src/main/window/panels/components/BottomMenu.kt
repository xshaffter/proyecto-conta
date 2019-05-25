package main.window.panels.components

import main.Global
import main.window.GraphicsAdapter
import main.window.panels.components.layouts.Layout
import java.awt.Color

class BottomMenu(width: Int, height: Int) : Layout(0, Global.window_height - height, width, height) {
    override fun actualizar() {
    }

    override fun dibujar(g: GraphicsAdapter) {
        g.fillRect(Color.DARK_GRAY, x, y, width, height)
        for (element in children) {
            element.dibujar(g)
        }
    }

    fun add(component: GComponent) {
        children.add(component)
    }
}