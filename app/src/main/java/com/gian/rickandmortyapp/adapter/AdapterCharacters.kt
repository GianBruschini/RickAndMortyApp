package com.gian.rickandmortyapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gian.rickandmortyapp.R
import com.gian.rickandmortyapp.databinding.CharacterItemBinding
import com.gian.rickandmortyapp.model.ApiResponse
import com.gian.rickandmortyapp.model.Results
import com.squareup.picasso.Picasso

class AdapterCharacters: RecyclerView.Adapter<AdapterCharacters.MyViewHolder>() {
    var listOfCharacters = ArrayList<Results>()

    fun setUpdatedData(characters:ArrayList<Results>){
        this.listOfCharacters = characters
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(CharacterItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso.get().
        load(listOfCharacters[position].image).
        into(holder.binding.imageOfCharacter)
        holder.binding.nameOfCharacter.text = listOfCharacters[position].name
        holder.binding.genreOfCharacter.text = "Genre: " + listOfCharacters[position].gender
        holder.binding.stateOfCharacter.text = "Status: " + listOfCharacters[position].status
        holder.binding.specieOfCharacter.text = "Species: " + listOfCharacters[position].species
        holder.binding.locationOfCharacter.text = "Location: " + listOfCharacters[position].location.name
    }

    override fun getItemCount(): Int {
        return listOfCharacters.size
    }

    class MyViewHolder(val binding: CharacterItemBinding):RecyclerView.ViewHolder(binding.root)



}