package main.game

import main.Global
import main.Global.window_height
import main.Global.window_width
import main.window.Ventana
import java.awt.Color
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException
import java.util.*
import kotlin.collections.ArrayList

class Juego {
    val rutas: Array<String> = arrayOf(

    )
    var letras = ('A'..'Z').toList()
    private val letrasOriginal = ('A'..'Z').toList()
    var palabra: Palabra = Global.rnd.next(Global.palabras)
    var palabraGuion: String = ""
    var fallos: Int = 0
    var palabraControl: CharArray = "".toCharArray()
    var palabraDisplay = ""

    init {
        reset()
    }

    fun reset() {
        letras = letrasOriginal.sorted()
        palabra = Global.rnd.next(Global.palabras)
        fallos = 0
        palabraGuion = ""
        palabraDisplay = ""
        palabraControl = palabra.palabra.toLowerCase().toCharArray()
        for (c in palabraControl) {
            if (c == ' ') {
                palabraGuion += "  "
                palabraDisplay += "  "
            } else {
                palabraGuion += "${c.toInt()} "
                palabraDisplay += "_ "
            }
        }
        Global.VENTANA?.reset()
    }

    fun intentar(letra: Char) {
        palabraDisplay = ""
        val letter = "${letra.toLowerCase().toInt()} "
        if (letter in palabraGuion) {
            palabraGuion = palabraGuion.replace(letter, "${letra.toLowerCase()} ")
        } else {
            fallos++
        }
        var numero = ""
        for (c in palabraGuion.toCharArray()) {
            if (c == ' ') {
                palabraDisplay += if (numero.isNumber()) {
                    "_ "
                } else {
                    "$numero "
                }
                numero = ""
            } else {
                numero += c
            }
        }
        if (!palabraGuion.containsNumbers()) {
            //"ganaste"
            reset()
        }
    }

    operator fun set(search: Char, newValue: Char) {
        val letras = letras.toCharArray()
        letras[letras.indexOf(search)] = newValue
        this.letras = letras.toList()
    }
}

private fun String.containsNumbers(): Boolean {
    if ('0'..'9' in this) {
        return true
    }
    return false
}

private operator fun String.contains(charRange: CharRange): Boolean {
    for (c in charRange) if (c in this) return true

    return false
}

private fun String.isNumber(): Boolean {
    return try {
        val s = Integer.parseInt(this)
        s + 1
        true
    } catch (nfe: NumberFormatException) {
        false
    }
}

private fun Random.next(list: ArrayList<Palabra>): Palabra {

    return try {
        list[(this.nextInt(list.size))]
    } catch (iae: IllegalArgumentException) {
        Palabra("", "")
    }

}
