package com.example.tecnologiasmoviles.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.tecnologiasmoviles.R
import com.example.tecnologiasmoviles.databinding.FragmentEjercicio2Binding
import com.google.android.material.textfield.TextInputLayout

class Ejercicio2Fragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_ejercicio2, container, false)

        val btn_calcular: Button = view.findViewById(R.id.btn_calcular)
        val btn_limpiar: Button = view.findViewById(R.id.btn_limpiar2)
        val inputLayoutC1: TextInputLayout = view.findViewById(R.id.til_cal1)
        val inputLayoutC2: TextInputLayout = view.findViewById(R.id.til_cal2)
        val inputLayoutC3: TextInputLayout = view.findViewById(R.id.til_cal3)
        val mostrarR: TextView = view.findViewById(R.id.tv_resultado_prom)
        val estatusR: TextView = view.findViewById(R.id.tv_estatus)

        val c1: EditText? = inputLayoutC1.editText
        val c2: EditText? = inputLayoutC2.editText
        val c3: EditText? = inputLayoutC3.editText

        btn_limpiar.setOnClickListener(){
            limpiar(view, c1, c2, c3)
            mostrarR.setText(R.string.relizaOpera)
            estatusR.setText(R.string.relizaOpera)
        }

        btn_calcular.setOnClickListener(){
            calcular(view, c1, c2, c3, mostrarR, estatusR)
        }

        return view
    }

    private fun limpiar(view: View,Cal1: EditText?, Cal2: EditText?, Cal3: EditText?){
        Cal1?.setText("")
        Cal1?.setSelection(0)
        Cal1?.requestFocus()
        Cal2?.setText("")
        Cal3?.setText("")

        val inputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun calcular(view: View,Cal1: EditText?, Cal2: EditText?, Cal3: EditText?, res: TextView, est: TextView){
        val Prom1 = Cal1?.text.toString().trim()
        val Prom2 = Cal2?.text.toString().trim()
        val Prom3 = Cal3?.text.toString().trim()

        if (!Prom1.isNullOrBlank() && !Prom2.isNullOrBlank() && !Prom3.isNullOrBlank()){
            val resultado = (Prom1.toDouble() + Prom2.toDouble() + Prom3.toDouble())/3
            res.setText(resultado.toString())

            if (resultado >= 9 ){
                est.setText(R.string.Excelente)
            }else if (resultado >= 8 ){
                est.setText(R.string.Bueno)
            }else if (resultado >= 7 ){
                est.setText(R.string.Regular)
            }else if (resultado >= 6 ){
                est.setText(R.string.Malo)
            }else{
                est.setText(R.string.Deficiente)
            }

            limpiar(view, Cal1, Cal2, Cal3)
        }else{
            Toast.makeText(this.context,"Es necesario llenar todos los campos primero", Toast.LENGTH_LONG).show()
        }
    }
}