package main.window

import com.jfoenix.controls.JFXTextField
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.text.TextAlignment
import javafx.stage.Stage
import main.Global
import javax.swing.GroupLayout
import main.Global.JUEGO as juego


class Ventana(ancho: Int, alto: Int) : Stage() {
    var letras: FlowPane
    var extras: FlowPane
    var ahorcado: Label
    var hint: Label
    var palabra: Label

    init {
        val mainLayout = BorderPane()
        val scene = Scene(mainLayout, ancho.toDouble(), alto.toDouble())
        val txtRespuesta = TextField()

        val menu = BorderPane()
        val header = FlowPane()
        val menuCenter = GridPane()
        val centerCenter = GridPane()
        val palabraContainer = FlowPane()
        val ahorcadoContainer = StackPane()
        val hintContainer = FlowPane()

        letras = FlowPane()
        extras = FlowPane()

        ahorcado = Label()
        hint = Label()
        palabra = Label()

        val center = BorderPane()

        txtRespuesta.background = Background(BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY))
        txtRespuesta.textProperty().addListener { _, _, _ ->
            if (txtRespuesta.text.length > 1) txtRespuesta.text = "${txtRespuesta.text[0]}"
        }

        header.alignment = Pos.CENTER
        header.children.add(txtRespuesta)
        menu.top = header


        letras.border = Global.BORDER
        letras.prefWidthProperty().bind(menuCenter.widthProperty().multiply(3).divide(5))
        letras.prefHeightProperty().bind(menuCenter.heightProperty())
        letras.children.addAll(getLetras())
        letras.hgap = Global.FONT.size

        extras.border = Global.BORDER
        extras.prefWidthProperty().bind(menuCenter.widthProperty().multiply(2).divide(5))
        extras.prefHeightProperty().bind(menuCenter.heightProperty())

        menuCenter.add(extras, 0, 0)
        menuCenter.add(letras, 1, 0)
        menu.center = menuCenter

        menu.background = Background(BackgroundFill(Color.valueOf("#333333"), CornerRadii.EMPTY, Insets.EMPTY))
        menu.prefHeightProperty().bind(heightProperty().multiply(2).divide(5))
        mainLayout.bottom = menu

        hint.text = juego.palabra.hint
        hint.textAlignment = TextAlignment.CENTER
        hint.isWrapText = true
        center.top = hint

        palabra.text = juego.palabra.palabra
        palabra.textAlignment = TextAlignment.CENTER
        palabra.alignment = Pos.CENTER_RIGHT
        palabra.isWrapText = true
        palabra.prefWidthProperty().bind(center.widthProperty().divide(2))

        ahorcadoContainer.prefWidthProperty().bind(center.widthProperty().subtract(palabraContainer.widthProperty()))

        centerCenter.add(ahorcadoContainer, 0, 0)
        centerCenter.add(palabra, 1, 0)
        center.center = centerCenter

        center.prefHeightProperty().bind(heightProperty().multiply(3).divide(5))
        mainLayout.center = center

        txtRespuesta.onAction = EventHandler {
            if (txtRespuesta.text.isNotEmpty()) {
                val letra = txtRespuesta.text.toUpperCase()[0]
                if (juego.letras.contains(letra)) {
                    juego[letra] = '_'
                }
                juego.intentar(letra)
                txtRespuesta.text = ""

                letras.children.clear()
                letras.children.addAll(getLetras())
                palabra.text = juego.palabraDisplay
            }
        }

        setScene(scene)
    }

    private fun getLetras(): ArrayList<Label> {
        val letrasList = ArrayList<Label>()
        juego.letras.forEach {
            val label = Label("$it")
            label.font = Global.FONT
            label.textFill = Color.WHITE
            letrasList += label
        }
        return letrasList
    }

    fun reset() {
        hint.text = juego.palabra.hint
        palabra.text = juego.palabraDisplay
    }

}
