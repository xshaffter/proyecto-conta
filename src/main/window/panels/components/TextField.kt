package main.window.panels.components

import java.awt.*
import java.awt.image.BufferedImage
import main.window.GraphicsAdapter
import main.Global


class TextField(imagen: BufferedImage?, private var maxLength: Int = -1, x: Int, y: Int) : GComponent(imagen, x, y) {


    var text = ""
    var hint = ""
    var font: Font? = null

    private val defaultOnAction = {
        onAction()
        text = ""
    }

    init {
        if (maxLength > 0) {
            updateSize(16 * maxLength, 16)
        }
    }

    override fun dibujar(g: GraphicsAdapter) {
        if (isVisible) {

            var maxString = ""

            for (i in 0 until maxLength) {
                maxString += "1"
            }

            val altoLetra = g.getStringHeight(text)
            val anchoTexto = g.stringWidth(text)
            val anchoTextoMax = g.stringWidth(maxString)
            val margenHorizontal: Byte = 2
            val posicionX = x + anchoTexto + margenHorizontal.toInt()
            val verticalCentered = ((height - altoLetra) / 2)
            val posicionYInicial = y + verticalCentered + 1
            val posicionYFinal = (y + altoLetra - verticalCentered)

            updateSize(anchoTextoMax + 2 * margenHorizontal, altoLetra + 2 * verticalCentered)
            g.borderedRect(Color.BLACK, Color.WHITE, x, y, width, height)

            if (text.isNotEmpty()) {
                g.drawText(Color.BLACK, text, x + margenHorizontal, posicionYFinal)
            } else if (!isFocused && hint.isNotEmpty()) {
                g.drawText(Color.GRAY, hint, x + margenHorizontal, posicionYFinal)
            }

            if (isFocused) {
                if (Math.floor(frame) % 2 == 0.0) {
                    g.drawCursor(posicionX, posicionYInicial, posicionYFinal)
                }
            }
        }
    }

    override fun actualizar() {
        if (isFocused) {
            val codigoTecla = Global.KEYBOARD.tecla
            val tecla: Char
            if (Global.KEYBOARD.isPressed) {
                when {
                    isWritable(codigoTecla) -> {
                        if (text.length < maxLength) {
                            tecla = codigoTecla.toChar()
                            text += tecla
                        }
                    }
                    codigoTecla == 8 -> {
                        try {
                            text = text.substring(0, text.length - 1)
                        } catch (ignored: StringIndexOutOfBoundsException) {
                        }
                    }
                    codigoTecla == 10 -> defaultOnAction()
                }
                Global.KEYBOARD.isPressed = false
            }
        }

        frame += 1.0 / Global.FPS_OBJETIVO
        if (frame == 2.0) {
            frame = 0.0
        }
    }

    private fun isWritable(codigoTecla: Int): Boolean {
        return codigoTecla in 32..126
    }
}