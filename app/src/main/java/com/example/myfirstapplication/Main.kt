package com.example.myfirstapplication


class abstract Piece(val largeur : Float,val longueur : Float,val nom : String)

open class Salon (nomsalon : String) : Piece(largeur,longueur,nom) {}
open class Cuisine (nomcuisine: String) : Piece(largeur, longueur, nom){}
val cuisine1 = Cuisine("CuisineBlanche")


fun main(){
    println("coucou")
    surface(7,3)
    val liste = listOf(cuisine1)
}

fun surface(largeur: Float, longueur: Float): Float {
    return longueur*largeur
}