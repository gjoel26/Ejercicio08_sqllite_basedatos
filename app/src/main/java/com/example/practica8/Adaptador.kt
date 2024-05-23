package com.example.practica8

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adaptador (private var mainActivity: MainActivity)
    : RecyclerView.Adapter<Adaptador.ViewHolderContactos>(){

    //Parte interna
    class ViewHolderContactos(item: View): RecyclerView.ViewHolder(item){
        var txtNombre : TextView = item.findViewById(R.id.txtNombre)
        var txtTelefono : TextView = item.findViewById(R.id.txtTelefono)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderContactos {
        var layoutItem = LayoutInflater.from(parent.context).inflate(R.layout.contacto_item, parent, false)
        return ViewHolderContactos(layoutItem)
    }

    override fun getItemCount(): Int  = ProvisionalDatos.listaContactos.size

    override fun onBindViewHolder(holder: ViewHolderContactos, position: Int) {
        val contacto = ProvisionalDatos.listaContactos[position]
        holder.txtNombre.text = contacto.nombre
        holder.txtTelefono.text = contacto.telefono
        holder.itemView.setOnClickListener{
            mainActivity.clickItem(position)
        }
    }
}