package main.config

import javafx.scene.control.Alert.AlertType
import javafx.scene.control.Alert
import main.Global
import main.game.Palabra
import java.io.IOException
import java.nio.file.LinkOption
import java.nio.file.Files
import java.nio.file.Paths
import java.io.File
import java.sql.*


class DBDriver {

    private var conn: Connection? = null
    private val ruta = System.getProperty("user.home") + "/Documents/"
    private val servidor: String = "jdbc:sqlite:$ruta"
    private val baseDatos: String = "ahorcado.db"

    init {
        disconnect()
        create()
        getResultados()
    }

    private fun connect() {
        try {
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

    private fun disconnect() {
        try {
            conn?.close()
            conn = null
        } catch (ex: SQLException) {
            val alert = Alert(AlertType.ERROR)
            alert.title = "No se ha podido cerrar la conexion"
            alert.contentText = "" + ex
        } catch (ignored: NullPointerException) {
        }

    }

    private fun create(): Boolean {
        return try {
            val carpeta = File(ruta)
            val success = carpeta.mkdirs()
            val path = Paths.get(carpeta.toURI())
            try {
                Files.setAttribute(path, "dos:hidden", java.lang.Boolean.TRUE, LinkOption.NOFOLLOW_LINKS)
            } catch (ignored: IOException) {
            }
            execute("CREATE TABLE 'resultados' ( `persona` VARCHAR ( 10 ) , `puntaje` INTEGER)")
            execute("CREATE TABLE 'palabras' ( `palabra` VARCHAR ( 50 ) , `hint` VARCHAR(255))")
            success
        } catch (sqlex: SQLException) {
            false
        }
    }

    @Throws(SQLException::class)
    private fun execute(query: String) {
        connect()
        val stmt = conn?.createStatement()
        stmt?.execute(query)
        disconnect()
    }

    fun getResultados() {
        connect()
        val rs: ResultSet?
        val query = "select * from resultados order by -puntaje"
        val statement = conn?.createStatement()
        rs = statement?.executeQuery(query)
        while (rs?.next() == true) {
            val resultado = Resultado(rs["persona"], rs["puntaje"])
            // Global.TABLON_RESULTADOS.put(resultado)
        }
        disconnect()
    }

    fun addResultado(persona: String, puntaje: Int) {
        connect()
        val query = "insert into resultados values(?,?)"
        val statement = conn?.prepareStatement(query)
        statement!![1] = persona
        statement[2] = puntaje
        statement.executeUpdate()
        disconnect()
    }

    fun addPalabra(palabra: String, hint: String) {
        connect()
        val query = "insert into palabras values(?,?)"
        val statement = conn?.prepareStatement(query)
        statement!![1] = palabra
        statement[2] = hint
        statement.executeUpdate()
        disconnect()
    }

    fun getPalabras() {
        connect()
        val rs: ResultSet?
        val query = "select * from palabras"
        val statement = conn?.createStatement()
        rs = statement?.executeQuery(query)
        while (rs?.next() == true) {
            Global.palabras += Palabra(rs["palabra"] ?: "", rs["hint"] ?: "")
        }
        disconnect()
    }
}

private operator fun PreparedStatement.set(key: Int, value: String) {
    setString(key, value)
}

private operator fun PreparedStatement.set(key: Int, value: Int) {
    setInt(key, value)
}

private operator fun ResultSet.get(key: String): String? {
    return getString(key)
}

data class Resultado(val persona: String?, val puntaje: String?) {
    override fun toString(): String {
        return "$persona || $puntaje"
    }
}