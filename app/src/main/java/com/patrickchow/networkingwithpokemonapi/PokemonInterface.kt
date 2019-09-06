package com.patrickchow.networkingwithpokemonapi

import com.google.gson.GsonBuilder
import com.patrickchow.networkingwithpokemonapi.Model.Pokemon
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonInterface {

    @GET("pokemon/{name-id}")
    fun getPokemonById(@Path("name-id") nameOrId: String): Call<Pokemon>

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