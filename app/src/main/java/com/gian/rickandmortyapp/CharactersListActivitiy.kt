package com.gian.rickandmortyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gian.rickandmortyapp.adapter.AdapterCharacters
import com.gian.rickandmortyapp.databinding.ActivityCharactersListActivitiyBinding
import com.gian.rickandmortyapp.model.ApiResponse
import com.gian.rickandmortyapp.viewmodel.CharacterListViewModel

class CharactersListActivitiy : AppCompatActivity() {
    private lateinit var binding: ActivityCharactersListActivitiyBinding
    private lateinit var recyclerAdapter: AdapterCharacters
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersListActivitiyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
       binding.recyclerViewCharacters.layoutManager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this,DividerItemDecoration.VERTICAL)
        binding.recyclerViewCharacters.addItemDecoration(decoration)
        recyclerAdapter = AdapterCharacters()
        binding.recyclerViewCharacters.adapter = recyclerAdapter
    }

    private fun initViewModel(){
        val viewModel=ViewModelProvider(this).get(CharacterListViewModel::class.java)
        viewModel.getApiResponseObserver().observe(this, Observer <ApiResponse>{
            if(it != null){
                recyclerAdapter.setUpdatedData(ArrayList(it.results))
            }else{
                Toast.makeText(this,"Error in getting data",Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiRickAndMortyCall()
    }




}