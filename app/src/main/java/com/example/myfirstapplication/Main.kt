/*package com.example.myfirstapplication


class abstract Piece(val largeur : Float,val longueur : Float,val nom : String)

open class Salon (nomsalon : String) : Piece(largeur,longueur,nom) {}
open class Cuisine (nomcuisine: String) : Piece(largeur, longueur, nom){}
val cuisine1 = Cuisine("CuisineBlanche")
val salon1 = Salon("Salon Vert")


class Etudiant(var name: String, var promo : String, var matières : List<String>)
val etudiants = listOf(
    Etudiant("Paul", "2025", listof("mobile","web","BDD")),
    Etudiant("Yazid", "2024", listof("mobile","Android","Réseau")),
    Etudiant("Caroline", "2025", listof("SE","Anglais"))
)


fun main(){
    println("coucou")
    surface(72,1.3)
    val liste = listOf(cuisine1)
    val liste = listOf(salon1)
    etudiants.filter { it.promo = "2024"}.map { it.name }.forEach(println(it))
    etudiants.filter {it.matières >=2}.map { it.name }.forEach(println(it))
    val nbtotalmatieres = etudiants.fold(0){acc, e -> acc + e.matières}

}



fun surface(largeur: Float, longueur: Float): Float {
    return longueur*largeur
}*/