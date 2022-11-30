package mx.edu.ittepic.ladm_u4_practica1_almacen_sms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import mx.edu.ittepic.ladm_u4_practica1_almacen_sms.databinding.ActivityNuevoMensajeBinding
import java.util.*

class NuevoMensaje : AppCompatActivity() {
    lateinit var binding: ActivityNuevoMensajeBinding
    val operacionesFirebase = OperacionesFirebase()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNuevoMensajeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.enviar.setOnClickListener { enviarMensaje(getMensaje()) }
        binding.cancelar.setOnClickListener { finish() }
    }

    private fun enviarMensaje(mensaje: Mensaje) {
        operacionesFirebase.guardarMensaje(mensaje)
            .addOnSuccessListener {
                Toast.makeText(this, "MENSAJE ENVIADO", Toast.LENGTH_LONG).show()
                finish()
            }
            .addOnFailureListener {
                AlertDialog.Builder(this).setTitle("ERROR")
                    .setMessage("Su mensaje no ha sido enviado, ¿Intentar de nuevo?")
                    .setPositiveButton("SI") { _, _ -> enviarMensaje(mensaje) }
                    .setNegativeButton("NO") { _, _ -> }
                    .show()
            }
    }

    private fun getMensaje(): Mensaje {
        return Mensaje(
            binding.destinatario.text.toString(),
            Date().toString(),
            binding.mensaje.text.toString(),
            null
        )
    }
}