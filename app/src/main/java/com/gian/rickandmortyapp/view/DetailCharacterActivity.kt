package com.gian.rickandmortyapp.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gian.rickandmortyapp.R
import com.gian.rickandmortyapp.databinding.ActivityDetailCharacterBinding
import com.squareup.picasso.Picasso

class DetailCharacterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailCharacterBinding
    private lateinit var name:String
    private lateinit var gender:String
    private lateinit var specie:String
    private lateinit var location:String
    private lateinit var origin:String
    private lateinit var status:String
    private lateinit var image:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getIntentValues()
        setUI()
    }

    private fun getIntentValues() {
        name = intent.getStringExtra("name").toString()
        gender = intent.getStringExtra("gender").toString()
        specie = intent.getStringExtra("specie").toString()
        location = intent.getStringExtra("location").toString()
        origin = intent.getStringExtra("origin").toString()
        status = intent.getStringExtra("status").toString()
        image = intent.getStringExtra("image").toString()
    }

    @SuppressLint("SetTextI18n")
    private fun setUI() {
        Picasso.get().load(image).into(binding.imageOfCharacter)
        binding.nameOfCharacter.text = "Name: $name"
        binding.genreOfCharacter.text = "Genre: $gender"
        binding.specieOfCharacter.text = "Specie: $specie"
        binding.locationOfCharacter.text = "Location: $location"
        binding.originOfCharacter.text = "Origin: $origin"
        binding.statusOfCharacter.text = "Status: $status"


    }
}