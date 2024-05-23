package com.example.practica8

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AgregarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_agregar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun guardar(v: View){
        val nombre = findViewById<EditText>(R.id.txtNombre)
        val telefono = findViewById<EditText>(R.id.txtTelefono)
        val contacto = Contacto(nombre.text.toString(), telefono.text.toString(), 0)

        Log.v("PRUEBAS", "Se presiono GUARDAR")
        val bd = openOrCreateDatabase("bd", MODE_PRIVATE, null)
        val parametros = ContentValues()
        parametros.put("nombre", contacto.nombre)
        parametros.put("telefono", contacto.telefono)
        bd.insert("Contactos", null, parametros)
        bd.close()
        //ProvisionalDatos.listaContactos.add(contacto)

        Toast.makeText(this, "Se guardo", Toast.LENGTH_LONG).show()
        finish()
    }
}