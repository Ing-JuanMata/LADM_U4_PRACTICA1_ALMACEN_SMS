package mx.edu.ittepic.ladm_u4_practica1_almacen_sms


import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class OperacionesFirebase {
    private val db = Firebase.firestore

    fun consultarMensajes() = db.collection("mensajes")

    fun guardarMensaje(mensaje: Mensaje) =
        db.collection("mensajes").add(mensaje.mapear())
}