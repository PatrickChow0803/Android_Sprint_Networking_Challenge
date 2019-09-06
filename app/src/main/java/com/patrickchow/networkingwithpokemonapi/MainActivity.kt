package com.patrickchow.networkingwithpokemonapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.patrickchow.networkingwithpokemonapi.Model.PokemonModel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

/*
    Steps Taken
    1. Create Pokemon Model
    2. Create Retrofit Instance
    3. Create Pokemon Interface
 */
class MainActivity : AppCompatActivity(), Callback<List<PokemonModel>> {
    override fun onFailure(call: Call<List<PokemonModel>>, t: Throwable) {
        Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show()
    }

    override fun onResponse(
        call: Call<List<PokemonModel>>,
        response: Response<List<PokemonModel>>
    ) {
       Toast.makeText(this, response.body()!![0].id, Toast.LENGTH_SHORT).show()
    }

    lateinit var pokemonService: PokemonInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pokemonService = PokemonInterface.Factory.create()

        btn_test.setOnClickListener {
            getPokemonById(1)
        }
    }

    fun getPokemonById(pokemonId: Int){
        pokemonService.getPokemonById(pokemonId.toString()).enqueue(this)
    }
}