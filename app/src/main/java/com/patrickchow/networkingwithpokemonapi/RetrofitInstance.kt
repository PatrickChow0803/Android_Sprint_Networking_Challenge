package com.patrickchow.networkingwithpokemonapi

import com.google.gson.GsonBuilder
import com.patrickchow.networkingwithpokemonapi.Model.PokemonModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
/*
object RetrofitInstance {
    private const val BASE_URL = "https://pokeapi.co/api/v2/"

    fun getPokemon():Call<PokemonModel>{
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(PokemonInterface::class.java).getPokemon()
    }
}*/