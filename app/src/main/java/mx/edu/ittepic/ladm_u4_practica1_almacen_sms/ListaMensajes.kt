package mx.edu.ittepic.ladm_u4_practica1_almacen_sms

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import mx.edu.ittepic.ladm_u4_practica1_almacen_sms.databinding.ActivityListaMensajesBinding

class ListaMensajes : AppCompatActivity() {
    lateinit var binding: ActivityListaMensajesBinding
    val operacionesFirebase = OperacionesFirebase()
    val mensajes = ArrayList<Mensaje>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaMensajesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        consultarMensajes()

        binding.nuevoMensaje.setOnClickListener {
            val activityNuevo = Intent(this, NuevoMensaje::class.java)
            startActivity(activityNuevo)
        }
    }

    fun consultarMensajes() {
        operacionesFirebase.consultarMensajes().addSnapshotListener { value, error ->

            val lista = ArrayList<String>()
            lista.add("NO HAY MENSAJES")
            if (error != null) {
                AlertDialog.Builder(this).setTitle("ERROR")
                    .setMessage("NO SE HA PODIDO CONSULTAR LOS MENSAJES:\n${error.message}")
                    .setPositiveButton("OK") { _, _ -> colocarMensajes(lista) }
                    .show()
                return@addSnapshotListener
            }

            if (value == null) {
                colocarMensajes(lista)
                return@addSnapshotListener
            }

            if (value.documents.size == 0) {
                mensajes.clear()
                colocarMensajes(lista)
                return@addSnapshotListener
            }

            lista.clear()
            mensajes.clear()
            value.documents.forEach { doc ->
                val mensaje = Mensaje(
                    doc.getString("destinatario")!!,
                    doc.getString("fecha")!!,
                    doc.getString("mensaje")!!,
                    doc.id
                )
                mensajes.add(mensaje)
                lista.add(mensaje.toString())
            }
            colocarMensajes(lista)
        }
    }

    private fun colocarMensajes(lista: java.util.ArrayList<String>) {
        binding.mensajes.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista)
    }
}