package com.gian.rickandmortyapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gian.rickandmortyapp.model.ApiResponse
import com.gian.rickandmortyapp.network.RetroInstance
import com.gian.rickandmortyapp.network.RetroService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterListViewModel: ViewModel() {
    var apiResponseListLiveData: MutableLiveData<ApiResponse> = MutableLiveData()

    fun getApiResponseObserver():MutableLiveData<ApiResponse>{
        return apiResponseListLiveData
    }

    fun makeApiRickAndMortyCall(page:Int){
        viewModelScope.launch (Dispatchers.IO){
                val retroInstance=
                    RetroInstance.getRetroInstance().create(RetroService::class.java)
                val response = retroInstance.getCharacters(page)
               apiResponseListLiveData.postValue(response)
        }
    }


}