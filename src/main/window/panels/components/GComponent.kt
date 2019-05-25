package main.window.panels.components

import main.Global
import java.awt.*
import java.awt.image.BufferedImage
import main.window.GraphicsAdapter

abstract class GComponent protected constructor(protected val imagenDefault: BufferedImage?, var x: Int, protected var y: Int) {

    constructor(x: Int, y: Int, width: Int, heigh: Int) : this(null, x, y) {
        updateSize(width, height)
    }

    private var ArrayList<GComponent>.isFocused: Boolean
        get() = true
        set(value) {
            for (component in this) {
                component.isFocused = value
            }
        }

    private var collisionBox: Rectangle? = null

    var onAction: () -> Unit = fun() {}
    protected var frame = 0.0
    var width: Int = 0
        private set
    var height: Int = 0
        private set
    var isFocused = false
    var isVisible = true

    protected var color: Color? = null
    protected val imagenInactivo: BufferedImage? = null
    protected val imagenActivo: BufferedImage? = null
    protected var imagenActual: BufferedImage? = null

    init {
        imagenActual = imagenDefault
        width = imagenDefault?.width ?: 0
        height = imagenDefault?.height ?: 0
    }

    open fun dibujar(g: GraphicsAdapter) {
        if (imagenActual != null) {
            g.drawImage(imagenActual, x, y)
        } else {
            g.fillRect(color, x, y, width, height)
        }
    }

    open fun actualizarBase() {
        if (Global.MOUSE.isHover(collisionBox) && Global.MOUSE.isClicked) {
            Global.VENTANA.lienzo.components.isFocused = false
            isFocused = true
        }
        actualizar()
    }

    abstract fun actualizar()

    fun updateSize(width: Int, height: Int) {
        this.width = width
        this.height = height
        collisionBox = Rectangle(x, y, width, height)
    }

}
