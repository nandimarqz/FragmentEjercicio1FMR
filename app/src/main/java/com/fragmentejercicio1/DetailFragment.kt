package com.fragmentejercicio1

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.fragmentejercicio1.databinding.FragmentDetailBinding



class DetailFragment : Fragment(R.layout.fragment_detail) {

    //Crea la constante para recoger del intent a la persona
    companion object{

        const val SELECTED_PERSON = "personaSeleccionada"

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Recuperamos el binding con bind por que la vista ya esta inflada
        val binding = FragmentDetailBinding.bind(view)

        //Recogemos la persona que se recibe por el argumento del fragment
        val person = arguments?.getParcelable<Person>(SELECTED_PERSON)

        //Si persona no es nula entra en la condicion y le cambia el nombre y la imagen al textView y a la imagen del layout
        if(person != null) {

            //Se cambia el titulo de la action bar por el nombre de la persona
            (requireActivity() as AppCompatActivity).supportActionBar?.title = person.nombre

            //Se cambia el texto del texView por el nombre de la persona
            binding.textView.text = person.nombre

            //Se cambia la url de la imagen
            Glide.with(binding.imageView).load(person.imagenUrl).into(binding.imageView)

            //Se pone un escuchador de eventos en el boton de llamada para cuando se haga click se realice lo siguiente
            binding.button.setOnClickListener {

                //Se guarda el intent en una variable(Este intent abre la app de llamada del tlf y le carga el numero de la persona)
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+person.tlf))

                //Inicia la actividad
                startActivity(intent)
            }

            //Se pone un escuchador de eventos en el boton de email para cuando se haga click se realice lo siguiente
            binding.button2.setOnClickListener {

                //Se guarda el intent en una variable
                //(Este intent abre la app de correo que tengamos en el tlf y abre un mail con destinatario el email de la persona)
                val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"+person.email))

                //Inicia la actividad
                startActivity(intent)

            }
        }

    }

}