package mx.edu.ittepic.ladm_u4_practica1_almacen_sms

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
}
