package com.patrickchow.networkingwithpokemonapi

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import com.patrickchow.networkingwithpokemonapi.Model.Pokemon
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.*
import java.io.IOException
import java.io.InputStream
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

/*
    Steps Taken
    1. Create Pokemon Model
    2. Create Pokemon Interface
    3. Create the method "getPokemonByNameOrId" to use the interface
    4. Implement Callback<Pokemon> and make appropriate functionality
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
            tv_pokemon_name.text = "Name: ${response.body()?.name.toString()}"
            tv_pokemon_id.text = "ID: ${response.body()?.id.toString()}"

            //Loop through the list of types and append it to a StringBuilder to set it as text for tv_pokemon_types
            val typesList = response.body()!!.types
            var sbTypes = StringBuilder()
            sbTypes.append("Types:\n")
            for(index in 0 until typesList.size)
                sbTypes.append("${typesList[index].type.name}\n")
            tv_pokemon_types.text = sbTypes

            //Loop through the list of abilities and append it to a StringBuilder to set it as text for tv_pokemon_abilities
            val abilitiesList = response.body()!!.abilities
            var sbAbilities = StringBuilder()
            sbAbilities.append("Abilities:\n")
            for(index in 0 until  abilitiesList.size)
                sbAbilities.append("${abilitiesList[index].ability.name}\n")
            tv_pokemon_abilities.text = sbAbilities

            imageURL = response.body()!!.sprites.front_default
            val serviceIntent = Intent(this, ImageDownloadService::class.java)
            this.startService(serviceIntent)

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

    companion object{
        var imageURL: String = ""
    }
}