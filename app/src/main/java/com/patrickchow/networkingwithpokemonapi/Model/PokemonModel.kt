package com.patrickchow.networkingwithpokemonapi.Model

//Names of values must match api.
//Can use @SerializedName("value") if you don't want to use the name required
data class PokemonModel (
    val name: String,
    val sprites: String,
    val id: Int,
    val abilities: List<String>,
    val types: List<String>
)