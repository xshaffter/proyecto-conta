package main.window

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
import main.Global.JUEGO as juego


class Ventana(ancho: Int, alto: Int) : Stage() {
    private var letras: FlowPane
    private var extras: FlowPane
    private var ahorcado: Label
    private var hint: Label
    private var palabra: Label
    var txtTimer: Label
        private set

    init {
        val mainLayout = BorderPane()
        val scene = Scene(mainLayout, ancho.toDouble(), alto.toDouble())
        val txtRespuesta = TextField()

        val menu = BorderPane()
        val header = FlowPane()
        val menuCenter = GridPane()
        val palabraContainer = FlowPane()
        val ahorcadoContainer = StackPane()
        val center = BorderPane()

        center.border = Global.BORDER

        letras = FlowPane()
        extras = FlowPane()
        txtTimer = Label("00:00")
        ahorcado = Label()
        hint = Label()
        palabra = Label()

        palabra.font = Global.FONT

        txtTimer.font = Global.FONT
        txtTimer.textFill = Color.WHITE
        txtRespuesta.background = Background(BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY))
        txtRespuesta.textProperty().addListener { _, _, _ ->
            if (txtRespuesta.text.length > 1) txtRespuesta.text = "${txtRespuesta.text[0]}"
        }

        txtRespuesta.alignment = Pos.CENTER
        txtTimer.alignment = Pos.CENTER_RIGHT

        header.alignment = Pos.CENTER
        header.children += txtTimer
        header.children += txtRespuesta
        menu.top = header


        letras.border = Global.BORDER
        letras.prefWidthProperty().bind(menu.widthProperty())
        letras.prefHeightProperty().bind(menuCenter.heightProperty())
        letras.alignment = Pos.TOP_CENTER
        letras.children.addAll(getLetras())
        letras.hgap = Global.FONT.size

        menuCenter.add(letras, 0, 0)
        menu.center = menuCenter

        menu.background = Background(BackgroundFill(Color.valueOf("#333333"), CornerRadii.EMPTY, Insets.EMPTY))
        menu.prefHeightProperty().bind(heightProperty().multiply(2).divide(5))
        menu.prefWidthProperty().bind(widthProperty())

        hint.text = juego.palabra.hint
        hint.textAlignment = TextAlignment.CENTER
        hint.isWrapText = true
        center.top = hint

        palabra.text = juego.palabra.palabra
        palabra.textAlignment = TextAlignment.CENTER
        palabra.alignment = Pos.CENTER_RIGHT
        palabra.isWrapText = true

        palabraContainer.alignment = Pos.CENTER
        palabraContainer.children += palabra
        palabraContainer.vgap = 300.0
        palabraContainer.prefWidthProperty().bind(center.widthProperty())

        center.center = ahorcadoContainer

        center.bottom = palabraContainer

        center.prefWidthProperty().bind(widthProperty())
        center.prefHeightProperty().bind(heightProperty().multiply(2).divide(5))
        mainLayout.center = center
        mainLayout.bottom = menu

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
        if (!Global.DEBUG) {
            fullScreenExitHint = ""
            isFullScreen = true
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

    fun setTime(time: String) {
        txtTimer.text = time
    }

}
