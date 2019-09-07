package com.patrickchow.networkingwithpokemonapi.Model

//Names of values must match api.
//Can use @SerializedName("value") if you don't want to use the name required
//Use https://pokeapi.co/api/v2/pokemon/1 as reference

import java.io.Serializable

data class Pokemon (

    val name: String,
    val sprites: Sprites,
    val id: Int,
    val abilities: List<ListOfAbilities>,
    val types: List<ListOfTypes>
)

data class SerializedPokemon(

    var name: String,
    var sprites: String,
    var id: Int,
    var ability: MutableList<String>,
    var type: MutableList<String>

):Serializable

data class Sprites (
    val front_default: String
)

data class Ability(
    val name: String
)

data class ListOfAbilities(
    val ability: Ability
)

data class ListOfTypes(
    val type: Type
)

data class Type(
    val name: String
)