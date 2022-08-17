package com.example.roomapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.roomapp.model.Beers
import com.example.roomapp.repository.Repository
import com.example.roomapp.roomdb.BeerEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val repository: Repository
): ViewModel() {

    var _BeersLiveData: MutableLiveData<Beers> = MutableLiveData(Beers())

    val readBeers: LiveData<List<BeerEntity>> = repository.localDataSource.readBeerFromDb().asLiveData()

    fun getBeersFromApi() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repository.remoteDataSource.apiDetails.getBeers()
                if (response.isSuccessful){
                    response.body().let {
                        addDataToDb(it!!)
                        _BeersLiveData.postValue(response.body())
                    }
                }
            } catch (e:Exception){

            }
        }
    }

    fun addDataToDb(beers: Beers){
        val beerEntity = BeerEntity(beers)
        CoroutineScope(Dispatchers.IO).launch{
            repository.localDataSource.insertBeerIntoDb(beerEntity)
        }
    }

}