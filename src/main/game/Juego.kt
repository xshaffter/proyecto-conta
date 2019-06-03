package main.game

import javafx.animation.Animation
import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.event.EventHandler
import javafx.util.Duration
import main.Global
import java.util.*

class Juego {

    var time = 0
        set(value) {
            Global.VENTANA.txtTimer.text = toMSF(time)
            field = value
        }
    val timer: Timeline
    val rutas: Array<String> = arrayOf(

    )
    var letras = ('A'..'Z').toList()
    private val letrasOriginal = ('A'..'Z').toList()
    var palabra: Palabra = Global.rnd.next(Global.palabras)
    var palabraGuion: String = ""
    var fallos: Int = 0
    var palabraControl: CharArray = "".toCharArray()
    var palabraDisplay = ""
    var tiempo: Int = 120
    var ronda = 1
    var erroresMaximos = 6

    init {
        reset()
        timer = Timeline(KeyFrame(Duration.millis(1000.0), EventHandler {
            if (time > 0) {
                time--
            }
        }))
    }

    fun reset() {
        letras = letrasOriginal.sorted()
        palabra = Global.rnd.next(Global.palabras)
        fallos = 0
        erroresMaximos = if (6 - ronda > 0) 6 - ronda else 1
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
        if (ronda >= 2) {
            time = /*tiempo - (15 * (ronda - 6))*/ 45
            if (ronda == 2) {
                timer.cycleCount = Animation.INDEFINITE
                timer.play()
            }
        }
    }

    fun intentar(letra: Char) {
        palabraDisplay = ""
        val letter = "${letra.toLowerCase().toInt()} "
        if (letter in palabraGuion) {
            palabraGuion = palabraGuion.replace(letter, "${letra.toLowerCase()} ")
        } else {
            fallos++
        }
        if (fallos >= erroresMaximos) {
            //perdiste
            Global.puntos = ronda
            ronda = 1
            timer.stop()
            time = 0

            reset()
            return
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
            ronda++
            reset()
        }
    }

    operator fun set(search: Char, newValue: Char) {
        val letras = letras.toCharArray()
        letras[letras.indexOf(search)] = newValue
        this.letras = letras.toList()
    }
}

private fun doble00Fmt(value: Int): String {
    return if (value < 10) {
        "0$value"
    } else {
        value.toString()
    }
}

private fun toMSF(time: Int): String {
    val minutos = doble00Fmt(time / 60)
    val segundos = doble00Fmt(time % 60)
    return "$minutos:$segundos"
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
