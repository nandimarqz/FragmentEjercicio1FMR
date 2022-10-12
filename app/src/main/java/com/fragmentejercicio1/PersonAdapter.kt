package com.fragmentejercicio1

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fragmentejercicio1.databinding.PersonViewBinding

/**
 * El adapter va a ser el puente entre la vista y los datos que tengamos(en este caso la vista:person_layout y los datos de la lista de la MainActivity )
 */
class PersonAdapter(val people : List<Person>, val listener: (Person) ->Unit): RecyclerView.Adapter<PersonAdapter.ViewHolder>() {


    /**
     * Esta funcion se encarga de inflar la vista en este caso person_view
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        //Guarda la vista inflada en una variable y la devolve pasandola como parametro a viewHolder para que controle la vista
        val view = LayoutInflater.from(parent.context).inflate(R.layout.person_view, parent, false)
        return ViewHolder(view)

    }

    /**
     * Se encarga de asignar un componente en el momento en el que la vista entra en pantalla
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //llama a la funcion bind creada abajo con la persona
        holder.bind(people[position])

        //Guarda a la persona en una variable
        val person = people[position]

        //Pone un escuchador de eventos para cuando se haga un click
        // en contacto llama a la funcion que se pasa por parametro a esta misma clase
        holder.itemView.setOnClickListener {
            listener(person)

        }


    }

    /**
     * Devuelve el tamaño de la lista pasada por parametro
     */
    override fun getItemCount(): Int = people.size

    /**
     * Gestiona la vista y controla lo que hay en pantalla, los datos, los reutiliza, etc
     */
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        //Obtiene los items de la vista en la variable
        private val binding = PersonViewBinding.bind(view)

        /**
         * Esta funcion cambia todos los valores de texto de las
         * etiquetas recogidas y cambia tambien la imagen con Glide
         */
        fun bind(person : Person){

            binding.email.text = person.email
            binding.tlf.text = person.tlf
            binding.name.text = person.nombre

            Glide.with(binding.image).load(person.imagenUrl).into(binding.image)

        }


    }

}