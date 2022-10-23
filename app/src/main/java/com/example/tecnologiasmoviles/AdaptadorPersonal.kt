package com.example.tecnologiasmoviles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorPersonal(private val items: ArrayList<Personal>): RecyclerView.Adapter<AdaptadorPersonal.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.vista_personalizada,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.id.text = item.id
        holder.nombre.text = item.nombre
        holder.edad.text = item.edad
        holder.carrera.text = item.carrera
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val id : TextView = view.findViewById(R.id.tv_id)
        val nombre : TextView = view.findViewById(R.id.tv_nombre)
        val edad : TextView = view.findViewById(R.id.tv_edad)
        val carrera : TextView = view.findViewById(R.id.tv_carrera)
    }
}