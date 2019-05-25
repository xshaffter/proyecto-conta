package main.window.panels.components.layouts

import main.window.GraphicsAdapter
import main.window.panels.components.GComponent

@Suppress("unused")
class GFlowLayout(x: Int, y: Int, width: Int, height: Int, private val orientation: Int, private val align: String) : Layout(
        x, y, width, height) {
    var hGap: Int = 0
    override fun actualizar() {
        when (orientation) {
            HORIZONTAL -> {
                var lastElement: GComponent? = null
                for (element in children) {
                    element.x = getAlignStart() + if (lastElement != null) {
                        (lastElement.x + lastElement.width)
                    } else {
                        0
                    } + hGap
                    lastElement = element
                }
            }
            VERTICAL -> {

            }
        }
    }

    constructor(x: Int, y: Int, width: Int, height: Int) : this(x, y, width, height, HORIZONTAL, START)
    constructor(x: Int, y: Int, width: Int, height: Int, orientation: Int) : this(x, y, width, height, orientation,
                                                                                  START)

    constructor(x: Int, y: Int, width: Int, height: Int, align: String) : this(x, y, width, height, HORIZONTAL, align)

    override fun dibujar(g: GraphicsAdapter) {
        for (element in children) {
            element.dibujar(g)
        }
    }

    private fun getAlignStart(): Int {
        return when (align) {
            START -> 0
            CENTER -> {
                if (orientation == VERTICAL) height else width / children.size
            }
            END -> if (orientation == VERTICAL) height else width
            else -> 0
        }
    }

    @Suppress("unused")
    companion object {
        const val VERTICAL = 0
        const val HORIZONTAL = 1
        const val START = "Start"
        const val CENTER = "Center"
        const val END = "End"
    }
}
