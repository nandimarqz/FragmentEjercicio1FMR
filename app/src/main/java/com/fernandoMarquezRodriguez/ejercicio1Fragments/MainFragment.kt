package com.fernandoMarquezRodriguez.ejercicio1Fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fragmentejercicio1.R
import com.fragmentejercicio1.databinding.FragmentMainBinding


class MainFragment : Fragment(R.layout.fragment_main) {

    //Crea estas variables para las fotos de perfil de los hombre y mujeres(son fotos al azar)
    val urlPerfilH = "https://loremflickr.com/g/320/240/paris,dog"
    val urlPerfilM = "https://loremflickr.com/g/320/240/paris,dog"

    //Crea la lista con personas
    val list = listOf<Person>(
        Person("Fernando", "625245368", "nandimarquez31@gmail.com", urlPerfilH),
                             Person("Pedro", "625145251", "pedrojurado@gmail.com", urlPerfilH),
                             Person("Lucia", "625154514" , "lucia@gmail.com", "$urlPerfilM?lock=1"),
                             Person("Diana", "64851224" , "dianasitamorenita@gmail.com", urlPerfilM),
                             Person("Pepe", "632155214" , "pepitogrillo@gmail.com", urlPerfilH)
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Recuperamos el binding con bind por que la vista ya esta inflada
        val binding = FragmentMainBinding.bind(view).apply{

            //Cambia el titulo de la action bar por el nombre de la aplicacion
            (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)

            //Asocia el adapter al recycler  y define la funcion que se le pasa por parametro a personAdapter
            //(que se llamara en el onBindViewHolder cuando se clicke sobre una persona)
                recycler.adapter = PersonAdapter(list){ person->

                    //llama a la funcion navigateTo pasandole la persona por parametro
                    navigateTo(person)

                }

        }


    }

    /**
     * Esta funcion se encarga de cargar el fragment DetailFragment en el container de los fragments
     * y le pasa al fragment una persona por el argumento en este caso la persona que recibe como
     * parametro la funcion
     */
    fun navigateTo(person : Person){

        //Realiza la navegacion con la accion realizada en nav_graph
        findNavController().navigate(
            R.id.action_mainFragment_to_detailFragment,
            bundleOf(DetailFragment.SELECTED_PERSON to  person)
        )

    }

}

