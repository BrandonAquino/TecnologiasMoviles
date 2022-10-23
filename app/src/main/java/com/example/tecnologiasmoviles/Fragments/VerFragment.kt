package com.example.tecnologiasmoviles.Fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tecnologiasmoviles.AdaptadorPersonal
import com.example.tecnologiasmoviles.Personal
import com.example.tecnologiasmoviles.R
import com.google.firebase.firestore.FirebaseFirestore

class VerFragment : Fragment() {
    private lateinit var adaptadorPersonal: AdaptadorPersonal
    private lateinit var recyclerView: RecyclerView
    private lateinit var lstPersona: ArrayList<Personal>

    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ver, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        InicializarDatos(view)
    }

    private fun InicializarDatos(view: View){
        lstPersona = ArrayList()
        adaptadorPersonal = AdaptadorPersonal(lstPersona)
        val layoutManager = LinearLayoutManager(context)

        db.collection("Personal").get().addOnSuccessListener { documentos ->
            for (documento in documentos){
                val objItem = documento.toObject(Personal::class.java)
                objItem.id = documento.id
                objItem.nombre = documento["Nombre"].toString()
                objItem.edad = documento["Edad"].toString()
                objItem.carrera = documento["Carrera"].toString()


                recyclerView = view.findViewById(R.id.rv_personas)
                recyclerView.adapter = adaptadorPersonal
                recyclerView.layoutManager = layoutManager
                lstPersona.add(objItem)
            }
        }
    }
}