package com.example.practica8

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    lateinit var rcv:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        rcv = findViewById(R.id.rvContactos)
    }//onCreate

    override fun onResume() {
        super.onResume()

        ProvisionalDatos.listaContactos.clear()

        val db = openOrCreateDatabase("bd", MODE_PRIVATE, null)
        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                "Contactos(id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "nombre TEXT NOT NULL, telefono TEXT NOT NULL);")
        val cursor = db.rawQuery("SELECT * FROM Contactos", null)

        while (cursor.moveToNext()){
            val id = cursor.getInt(0)
            val nombre = cursor.getString(1)
            val telefono = cursor.getString(2)
            val contacto = Contacto(nombre, telefono, id)
            ProvisionalDatos.listaContactos.add(contacto)
        }

        Log.w("Contactos", "Hay ${ProvisionalDatos.listaContactos.size} contactos")

        val gson = Gson()
        val contenido = gson.toJson(ProvisionalDatos.listaContactos)
        Log.v("PRUEBAS", contenido)

        db.close()

        rcv.adapter = Adaptador(this)
        rcv.layoutManager = LinearLayoutManager(this)
    }

    fun btnAgregar(v: View){
        val intent = Intent(this, AgregarActivity::class.java)
        startActivity(intent)
    }

    fun clickItem(position: Int){
        val intent = Intent(this, EditActivity::class.java)
        intent.putExtra("position", position)
        startActivity(intent)
    }
}