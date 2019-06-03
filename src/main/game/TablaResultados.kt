package main.game

import javafx.geometry.Orientation
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.layout.FlowPane
import javafx.stage.Stage

class TablaResultados : Stage() {
    private val resultados = FlowPane(Orientation.VERTICAL)

    init {
        title = "Tabla de resultados"
        val tabla = FlowPane(Orientation.VERTICAL)
        val header = FlowPane()
        val persona = Label("Nombre")
        val score = Label("Puntuacion")

        header.children += persona
        header.children += score
        persona.prefWidthProperty().bind(widthProperty().divide(2))
        score.prefWidthProperty().bind(widthProperty().divide(2))

        tabla.children += header
        scene = Scene(tabla)
        show()

    }

    fun display() {

        resultados.children.clear()

        resultados.children.addAll()

        show()
    }
}