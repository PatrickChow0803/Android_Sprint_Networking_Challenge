package com.patrickchow.networkingwithpokemonapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import com.patrickchow.networkingwithpokemonapi.Model.Pokemon
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.*

/*
    Steps Taken
    1. Create Pokemon Model
    2. Create Pokemon Interface
    3. Create the method "getPokemonByNameOrId" to use the interface
    4. Implement Callback<Pokemon> and make appropriate functionality
 */
class MainActivity : AppCompatActivity(), Callback<Pokemon>{
    override fun onFailure(call: Call<Pokemon>, t: Throwable) {
        Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show()
    }

    override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
        if(response.isSuccessful)
            tv_pokemon_name.text = response.body()?.name.toString()
        else {
            val errorToast = Toast.makeText(applicationContext, "Invalid Name or ID", Toast.LENGTH_SHORT)
            errorToast.setGravity(Gravity.CENTER, 0, 0)
            errorToast.show()
        }
    }

    lateinit var pokemonService: PokemonInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pokemonService = PokemonInterface.create()

        btn_test.setOnClickListener {
            getPokemonByNameOrId(et_pokemon_name_or_id.text.toString())
        }
    }

    fun getPokemonByNameOrId(pokemonId: String){
        pokemonService.getPokemonById(pokemonId).enqueue(this)
    }
}