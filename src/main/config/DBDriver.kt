package main.config

import javafx.scene.control.Alert.AlertType
import javafx.scene.control.Alert
import java.sql.Connection
import java.sql.SQLException
import java.sql.DriverManager



class DBDriver {

    private var conn: Connection? = null
    private val servidor: String? = null, private
    val baseDatos: String? = null

    init {
        disconnect()
    }

    fun connect() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver")
            conn = DriverManager.getConnection(servidor + baseDatos)
        } catch (ex: ClassNotFoundException) {
            val alert = Alert(AlertType.ERROR)
            alert.title = "No se ha encontrado el serivor"
            alert.contentText = "" + ex
        } catch (ex: SQLException) {
            val alert = Alert(AlertType.ERROR)
            alert.title = "No se ha podido conectar con la base de datos"
            alert.contentText = "" + ex
        }

    }
}