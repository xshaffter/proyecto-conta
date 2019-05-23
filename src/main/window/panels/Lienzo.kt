package main.window.panels

import main.Global
import main.window.GraphicsAdapter
import main.window.panels.components.GComponent
import java.awt.Canvas
import java.awt.Color
import java.util.*

class Lienzo(private val ancho: Int, private val alto: Int) : Canvas() {
    val components = ArrayList<GComponent>()

    init {
        setSize(ancho, alto)

        this.addMouseListener(Global.MOUSE)
        this.addKeyListener(Global.KEYBOARD)
        this.requestFocus()
    }

    fun add(component: GComponent) {
        components.add(component)
    }

    fun dibujar() {
        val bs = this.bufferStrategy

        if (bs == null) {
            this.createBufferStrategy(3)
            return
        }
        val g = GraphicsAdapter(bs.drawGraphics)
        val altoMenu = 128

        g.fillRect(Color.cyan, 0, 0, ancho, alto)

        g.fillRect(Color.DARK_GRAY, 0, alto - altoMenu, ancho, altoMenu)

        var y = 1
        var x = 0
        for (c in Global.JUEGO.letras) {
            g.drawText(Color.WHITE, c.toString(), 32 * x, alto - altoMenu + 32 + 32 * y)
            if (x % 12 == 0 && x != 0) {
                y++
                x = -1
            }
            x++
        }

        for (component in components) {
            component.dibujar(g)
        }
        val fallos = 0
        for (componente in 0..fallos) {
            val func = Global.JUEGO.ahorcado[componente]
            func(g)
        }

        g.dispose()
        bs.show()
    }

    fun actualizar() {
        Global.MOUSE.actualizar(this)

        for (component in components) {
            component.actualizarBase()
        }
    }
}