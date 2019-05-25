package main.window.panels.components.layouts

import main.window.panels.components.GComponent


abstract class Layout(x: Int, y: Int, width: Int, height: Int) : GComponent(x, y, width, height) {
    val children = ArrayList<GComponent>()
    override fun actualizarBase() {
        for (element in children) {
            element.actualizarBase()
        }
        actualizar()
    }
}