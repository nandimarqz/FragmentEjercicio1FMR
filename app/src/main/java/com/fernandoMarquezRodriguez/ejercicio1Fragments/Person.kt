package com.fernandoMarquezRodriguez.ejercicio1Fragments

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(val nombre : String, val tlf : String, val email:String, val imagenUrl : String):Parcelable {
}