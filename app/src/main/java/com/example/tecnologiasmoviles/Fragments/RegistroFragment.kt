package com.example.tecnologiasmoviles.Fragments

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tecnologiasmoviles.AdaptadorPersonal
import com.example.tecnologiasmoviles.Personal
import com.example.tecnologiasmoviles.R
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class RegistroFragment : Fragment() {

    private lateinit var lstPersona: ArrayList<Personal>
    private lateinit var adaptadorPersonal: AdaptadorPersonal
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_registro, container, false)
        val btn_guardar: Button = view.findViewById(R.id.btn_guardar)
        val btn_limpiar: Button = view.findViewById(R.id.btn_limpiar)
        val inputLayoutN: TextInputLayout = view.findViewById(R.id.til_nombre)
        val inputLayoutE: TextInputLayout = view.findViewById(R.id.til_edad)
        val inputLayoutC: TextInputLayout = view.findViewById(R.id.til_carrera)

        val N : EditText? = inputLayoutN.editText
        val E: EditText? = inputLayoutE.editText
        val C: EditText? = inputLayoutC.editText

        btn_limpiar.setOnClickListener(){
            limpiar(N,E,C, view)
        }

        btn_guardar.setOnClickListener(){
            registro(N, E, C, view)
        }
        return view
    }

    private fun registro(N: EditText?, E: EditText?, C: EditText?, view: View){

        val Nombre = N?.text.toString().trim()
        val Edad = E?.text.toString().trim()
        val Carrera = C?.text.toString().trim()
        lstPersona = ArrayList()
        adaptadorPersonal = AdaptadorPersonal(lstPersona)

        if (!Nombre.isNullOrBlank() && !Edad.isNullOrBlank() && !Carrera.isNullOrBlank()){
            val persona = hashMapOf(
                "Nombre" to Nombre,
                "Edad" to Edad,
                "Carrera" to Carrera
            )

            db.collection("Personal")
                .add(persona)
                .addOnSuccessListener { documentReference ->
                    Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                    Toast.makeText(this.context, "Se a guardado con exito el registro", Toast.LENGTH_LONG).show()
                    lstPersona.add(Personal(documentReference.id,Nombre,Edad,Carrera))
                    adaptadorPersonal.notifyDataSetChanged()
                    limpiar(N, E, C, view)
                }
                .addOnFailureListener { e ->
                    Log.w(ContentValues.TAG,"Error al añadir el documento", e)
                    Toast.makeText(this.context,"No se pudo guardar el registro", Toast.LENGTH_LONG).show()
                }
        }else{
            Toast.makeText(this.context,"Es necesario llenar todos los campos primero", Toast.LENGTH_LONG).show()
        }

    }

    private fun limpiar(Nombre: EditText?, Edad: EditText?, Carrera: EditText?, view: View){
        Nombre?.setText("")
        Nombre?.setSelection(0)
        Nombre?.requestFocus()
        Edad?.setText("")
        Carrera?.setText("")

        val inputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}