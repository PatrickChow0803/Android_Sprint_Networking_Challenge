package com.patrickchow.networkingwithpokemonapi

import com.google.gson.GsonBuilder
import com.patrickchow.networkingwithpokemonapi.Model.PokemonModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonInterface {

    @GET("pokemon/{id}")
    fun getPokemonById(@Path("id") id: String): Call<List<PokemonModel>>

    class Factory{
        companion object{
            val BASE_URL: String = "https://pokeapi.co/api/v2/"

            fun create(): PokemonInterface{
                val gson = GsonBuilder()
                    .setLenient()
                    .create()

                return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                    .create(PokemonInterface::class.java)
            }
        }
    }
}