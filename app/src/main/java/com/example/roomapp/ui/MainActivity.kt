package com.example.roomapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.roomapp.R
import com.example.roomapp.databinding.ActivityMainBinding
import com.example.roomapp.model.Beers
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by viewModels()
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getBeers()
        viewModel.readBeers.observe(this@MainActivity) {
            if (it.isNotEmpty()) {
//                    Toast.makeText(this,"data from database", Toast.LENGTH_SHORT).show()
                updateUI(it[0].beerEntityModel)
            } else {
//                throw Throwable("No data")
            }
        }
    }

    fun getBeers(){
        viewModel.getBeersFromApi()
//        viewModel.readBeers.observe(this){
//            beers -> updateUI(beers as Beers)
//        }
//        viewModel._BeersLiveData.observe(this){
//            beers -> updateUI(beers as Beers)
//        }
    }
    fun updateUI(beers: Beers){
        var text = ""
        for(beer in beers){
            text += "${beer.id} ${beer.name}\n"
        }
        binding.tvMain.text = text
    }
}