package mx.edu.ittepic.ladm_u4_practica1_almacen_sms

import android.telephony.SmsManager

data class Mensaje(val destinatario: String, val fecha: String, val mensaje: String, val id: String?) {
    override fun toString(): String {
        return "Destinatario: $destinatario\n$mensaje\n$fecha"
    }

    fun mapear(): HashMap<String, String> {
        return hashMapOf(
            "destinatario" to destinatario,
            "mensaje" to mensaje,
            "fecha" to fecha
        )
    }

    fun enviar(){
        val smsManager = SmsManager.getDefault()
        smsManager.sendTextMessage(destinatario, null, mensaje, null, null)
    }
}
