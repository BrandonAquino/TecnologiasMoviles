package com.example.tecnologiasmoviles

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.tecnologiasmoviles.Fragments.Ejercicio2Fragment
import com.example.tecnologiasmoviles.Fragments.InicioFragment
import com.example.tecnologiasmoviles.Fragments.RegistroFragment
import com.example.tecnologiasmoviles.Fragments.VerFragment
import com.example.tecnologiasmoviles.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTabs(binding)
    }

    private fun setTabs(binding: ActivityMainBinding){
        val adaptador = AdaptadorDeFragments(supportFragmentManager)
        adaptador.añadirFragment(InicioFragment(),"Inicio")
        adaptador.añadirFragment(RegistroFragment(),"Registro")
        adaptador.añadirFragment(VerFragment(),"Ver")
        adaptador.añadirFragment(Ejercicio2Fragment(),"Promedio")
        binding.viewPager.adapter = adaptador
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        binding.tabLayout.getTabAt(0)!!.setIcon(R.drawable.inicio_blanco)
        binding.tabLayout.getTabAt(1)!!.setIcon(R.drawable.agregar_persona_blanco)
        binding.tabLayout.getTabAt(2)!!.setIcon(R.drawable.lista_blanco)
        binding.tabLayout.getTabAt(3)!!.setIcon(R.drawable.calificaciones_blanco)
    }
}