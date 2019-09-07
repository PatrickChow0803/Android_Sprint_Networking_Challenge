package com.patrickchow.networkingwithpokemonapi

import android.app.DownloadManager
import android.content.BroadcastReceiver
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.patrickchow.networkingwithpokemonapi.Model.SerializedPokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        //Get the pokemon information passed from MainActivity
        val pokemonDetails = intent.getSerializableExtra("mySerializedPokemonKey") as? SerializedPokemon

        tv_pokemon_name.text = "Name: ${pokemonDetails?.name.toString()}"
        tv_pokemon_id.text = "ID: ${pokemonDetails?.id.toString()}"

        //Loop through the list of types and append it to a StringBuilder to set it as text for tv_pokemon_types
        val typesList = pokemonDetails!!.type
        var sbTypes = StringBuilder()
        sbTypes.append("Types:\n")
        for(index in 0 until typesList.size)
            sbTypes.append("${typesList[index]}\n")
        tv_pokemon_types.text = sbTypes

        //Loop through the list of abilities and append it to a StringBuilder to set it as text for tv_pokemon_abilities
        val abilitiesList = pokemonDetails!!.ability
        var sbAbilities = StringBuilder()
        sbAbilities.append("Abilities:\n")
        for(index in 0 until  abilitiesList.size)
            sbAbilities.append("${abilitiesList[index]}\n")
        tv_pokemon_abilities.text = sbAbilities

        //Load the image using Picasso. Give it the URL and tell it to place it into iv_pokemon_image
        Picasso.get().load(pokemonDetails?.sprites).into(iv_pokemon_image)
    }

}
