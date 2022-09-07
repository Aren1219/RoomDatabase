package com.example.roomapp.ui

import androidx.lifecycle.*
import com.example.roomapp.model.Beers
import com.example.roomapp.roomdb.BeerEntity
import com.example.roomdatabase.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val repository: Repository
): ViewModel() {

    var beersLiveData: LiveData<BeerEntity> = repository.readBeersFromDb()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            if (repository.getBeersFromDb().beersList.isNullOrEmpty()){
                getBeersFromApi()
            }
        }
    }

    private suspend fun getBeersFromApi(){
        val response = repository.getBeersFromApi()
        if(response.isSuccessful){
            insertIntoDatabase(response.body()!!)
        }
    }

    private suspend fun insertIntoDatabase(beers:Beers){
        val beerEntity = BeerEntity(beers)
        repository.insertBeers(beerEntity)
    }
}