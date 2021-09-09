package com.gian.rickandmortyapp.view

import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gian.rickandmortyapp.adapter.AdapterCharacters
import com.gian.rickandmortyapp.databinding.ActivityCharactersListActivitiyBinding
import com.gian.rickandmortyapp.model.Results
import com.gian.rickandmortyapp.viewmodel.CharacterListViewModel
import kotlin.properties.Delegates

class CharactersListActivitiy : AppCompatActivity(),AdapterCharacters.OnItemClickListener {
    private lateinit var binding: ActivityCharactersListActivitiyBinding
    private lateinit var recyclerAdapter: AdapterCharacters
    private var currentPage by Delegates.notNull<Int>()
    private lateinit var viewModel: CharacterListViewModel


    private lateinit var scrollListener: RecyclerView.OnScrollListener
    private var lastVisibleItemPosition: Int = 0
    private var page: Int = 1

    var listOfCharacters = ArrayList<Results>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersListActivitiyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(userHasInternetConnection()){
            viewModel = ViewModelProvider(this)[CharacterListViewModel::class.java]
            currentPage = 1
            initRecyclerView()
            initViewModel()

        }else{
            showAlertDialog()
        }

    }



    private fun showAlertDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage("Get Internet connection to proceed or close the App")
            .setCancelable(false)
            .setPositiveButton("Proceed", DialogInterface.OnClickListener {
                    dialog, id -> val intent = intent
                finish()
                startActivity(intent)
            })
            .setNegativeButton("Close app", DialogInterface.OnClickListener {
                    dialog, id -> finish()
            })

        val alert = dialogBuilder.create()
        alert.setTitle("You don't have Internet connection")
        alert.show()
    }

    private fun initRecyclerView() {
        val gridLayoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        binding.recyclerViewCharacters.layoutManager = gridLayoutManager
        binding.recyclerViewCharacters.setHasFixedSize(true)
        recyclerAdapter = AdapterCharacters(this)
        binding.recyclerViewCharacters.adapter = recyclerAdapter
        recyclerAdapter.setOnItemClickListener(this)


        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = recyclerView.layoutManager!!.itemCount

                lastVisibleItemPosition = (binding.recyclerViewCharacters.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                if (totalItemCount == lastVisibleItemPosition + 1) {
                    page += 1
                    if(page<35){
                        viewModel.makeApiRickAndMortyCall(page)
                    }
                }
            }
        }
        binding.recyclerViewCharacters.addOnScrollListener(scrollListener)

    }

    private fun initViewModel(){
        viewModel.getApiResponseObserver().observe(this, Observer { currentResponse ->
            if(currentResponse != null){
                recyclerAdapter.setUpdatedData(ArrayList(currentResponse.results))
                listOfCharacters.addAll(currentResponse.results)
            }else{
                Toast.makeText(this,"Error in getting data",Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.makeApiRickAndMortyCall(currentPage)
    }

    fun userHasInternetConnection(): Boolean {
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if(networkInfo != null && networkInfo.isConnected) {
            return true
        }
        return false
    }

    override fun onitemClick(position: Int) {
        val intent = Intent(this,DetailCharacterActivity::class.java)
        intent.putExtra("name",listOfCharacters[position].name)
        intent.putExtra("gender",listOfCharacters[position].gender)
        intent.putExtra("image",listOfCharacters[position].image)
        intent.putExtra("specie",listOfCharacters[position].species)
        intent.putExtra("status",listOfCharacters[position].status)
        intent.putExtra("origin",listOfCharacters[position].origin.name)
        intent.putExtra("location",listOfCharacters[position].location.name)
        startActivity(intent)
    }
}