package main.game

import main.window.GraphicsAdapter
import java.awt.Color

class Juego {
    val ahorcado: Array<(GraphicsAdapter) -> Unit> = arrayOf(
            { g: GraphicsAdapter ->
                //base
                val grosor = 10
                val altoPantalla = 300
                val altoMenu = 128
                val altoMastil = 150
                val largoBase = 100
                val largoBaseSuperior = 50
                val largoHorca = 30
                val xBase = 10
                val xMastil = xBase + largoBase / 2 - grosor / 2
                val xBasesuperior = xMastil + grosor
                val xHorca = xBasesuperior + largoBaseSuperior - grosor

                g.fillRect(Color.BLACK, xBase, altoPantalla - altoMenu - grosor, largoBase, grosor)
                g.fillRect(Color.BLACK, xMastil, altoPantalla - altoMenu - grosor - altoMastil,
                           grosor, altoMastil)
                g.fillRect(Color.BLACK, xBasesuperior, altoPantalla - altoMenu - grosor - altoMastil, largoBaseSuperior,
                           grosor)
                g.fillRect(Color.BLACK, xHorca, altoPantalla - altoMenu - altoMastil, grosor, largoHorca)
            },
            { g ->
                //cabeza

            }

    )
    var letras = ('A'..'Z').toList()


}
