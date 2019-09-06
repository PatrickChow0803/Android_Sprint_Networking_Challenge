package com.patrickchow.networkingwithpokemonapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.patrickchow.networkingwithpokemonapi.Model.Pokemon
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

/*
    Steps Taken
    1. Create Pokemon Model
    2. Create Retrofit Instance
    3. Create Pokemon Interface
 */
class MainActivity : AppCompatActivity(), Callback<Pokemon>{
    override fun onFailure(call: Call<Pokemon>, t: Throwable) {
        Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show()
    }

    override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
        tv_pokemon_name.text = response.body()?.name.toString()
    }

    lateinit var pokemonService: PokemonInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pokemonService = PokemonInterface.create()

        btn_test.setOnClickListener {
            getPokemonById(1)
        }
    }

    fun getPokemonById(pokemonId: Int){
        pokemonService.getPokemonById(pokemonId.toString()).enqueue(this)
    }
}