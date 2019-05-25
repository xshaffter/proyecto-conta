package main.window.panels.components.layouts

import main.Global
import main.window.GraphicsAdapter

class GBorderLayout(x: Int, y: Int, width: Int, height: Int) : Layout(x, y, width, height) {
    val weight = 128
    var top: Layout = GFlowLayout(0, 0, Global.window_width, weight)
    var center: Layout = GFlowLayout(weight, weight, Global.window_width - weight, Global.window_height - weight)
    var bottom: Layout = GFlowLayout(0, Global.window_height - weight, Global.window_width, weight)
    var left: Layout = GFlowLayout(Global.window_width - weight, weight, weight, Global.window_height - 2 * weight)
    var right: Layout = GFlowLayout(0, weight, weight, Global.window_height - 2 * weight)
    override fun actualizar() {
        top.actualizarBase()
        center.actualizarBase()
        bottom.actualizarBase()
        left.actualizarBase()
        right.actualizarBase()

    }

    override fun dibujar(g: GraphicsAdapter) {
        top.dibujar(g)
        center.dibujar(g)
        bottom.dibujar(g)
        left.dibujar(g)
        right.dibujar(g)
    }
}