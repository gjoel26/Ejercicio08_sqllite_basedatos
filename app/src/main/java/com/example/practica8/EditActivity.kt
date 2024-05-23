package com.example.practica8

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EditActivity : AppCompatActivity() {
    var position : Int = 0
    var id: Int = 0
    lateinit var txtNombre: EditText
    lateinit var txtTelefono: EditText

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar)

        val txtTitulo = findViewById<TextView>(R.id.txtTitulo)
        txtTitulo.text = "Modificar Contacto"

        position = intent.getIntExtra("position", -1)
        Log.e("Contacto", "Se recibio un ${position}")
        txtNombre = findViewById(R.id.txtNombre)
        txtTelefono = findViewById(R.id.txtTelefono)

        val contacto = ProvisionalDatos.listaContactos[position]
        txtNombre.setText(contacto.nombre)
        txtTelefono.setText(contacto.telefono)
        id = contacto.id
    }

    fun guardar(v:View){
        val nombre = txtNombre.text.toString()
        val telefono = txtTelefono.text.toString()

        val bd = openOrCreateDatabase("bd", MODE_PRIVATE, null)

        // Crear un objeto ContentValues con los nuevos datos del contacto
        val parametros = ContentValues()
        parametros.put("nombre", nombre)
        parametros.put("telefono", telefono)

        val cantidadFilasActualizadas = bd.update(
            "Contactos",
            parametros,
            "id = ?",
            arrayOf(id.toString())
        )

        bd.close()

        if (cantidadFilasActualizadas > 0) {
            Toast.makeText(this, "Contacto modificado correctamente", Toast.LENGTH_SHORT).show()

            // Actualizar los datos del contacto en la lista de contactos
            ProvisionalDatos.listaContactos[position].nombre = nombre
            ProvisionalDatos.listaContactos[position].telefono = telefono

            finish()
        } else {
            Toast.makeText(this, "Error al modificar el contacto", Toast.LENGTH_SHORT).show()
        }
    }

}