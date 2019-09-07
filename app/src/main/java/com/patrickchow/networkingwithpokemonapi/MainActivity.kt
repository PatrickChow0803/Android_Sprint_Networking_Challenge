package com.patrickchow.networkingwithpokemonapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import com.patrickchow.networkingwithpokemonapi.Model.Pokemon
import com.patrickchow.networkingwithpokemonapi.Model.SerializedPokemon
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.*


/*
    Steps Taken
    1. Create Pokemon Model
    2. Create Pokemon Interface
    3. Create the method "getPokemonByNameOrId" to use the interface
    4. Implement Callback<Pokemon> and make appropriate functionality
    5. Move to Details Activity to display the details of the searched Pokemon
 */

class MainActivity : AppCompatActivity(), Callback<Pokemon>{
    //This is called when the request is failed before even being able to request something from the API
    //Usually means that either the model is wrong or the interface is.
    override fun onFailure(call: Call<Pokemon>, t: Throwable) {
        Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show()
    }

    //This is called when a response happens. So after the API receives your request
    override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
        if(response.isSuccessful) {

            val searchedPokemon = response.body() as Pokemon


            val addedPokemon = TextView(this)

            addedPokemon.text = searchedPokemon.name
            addedPokemon.textSize = 20f
            addedPokemon.setOnClickListener {
                //Hide the selected pokemon from the list if it is touched
                addedPokemon.height = 0
                Toast.makeText(this, "Pokemon has been removed from favorites", Toast.LENGTH_SHORT).show()
            }
            ll_searched_pokemon.addView(addedPokemon)

            //Get all of the abilities and place them into a mutable list
            val listOfAbilities = mutableListOf<String>()
            searchedPokemon.abilities.forEach {
                listOfAbilities.add(it.ability.name)
            }

            //Get all of the types and place them into a mutable list
            val listOfTypes = mutableListOf<String>()
            searchedPokemon.types.forEach {
                listOfTypes.add(it.type.name)
            }

            //Make a serialized pokemon in order to move an object to another activity
            val mySerializedPokemon = SerializedPokemon(
                searchedPokemon.name,
                searchedPokemon.sprites.front_default,
                searchedPokemon.id,
                listOfAbilities,
                listOfTypes
            )

            //Now go to the details activity and pass in information for the intent
            val detailsIntent = Intent(this, DetailsActivity::class.java)
            detailsIntent.putExtra("mySerializedPokemonKey", mySerializedPokemon)
            startActivity(detailsIntent)

        }
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