package com.example.tecnologiasmoviles

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class AdaptadorDeFragments(supportFragmentManager: FragmentManager) : FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val listaFragment = ArrayList<Fragment>()
    private val titulolistaFragment = ArrayList<String>()

    override fun getItem(position: Int): Fragment {
        return listaFragment[position]
    }

    override fun getCount(): Int {
        return listaFragment.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titulolistaFragment[position]
    }

    fun añadirFragment(fragment: Fragment, titulo: String){
        listaFragment.add(fragment)
        titulolistaFragment.add(titulo)
    }
}