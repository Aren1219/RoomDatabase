package com.example.roomapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomapp.adapter.BeerItemAdapter
import com.example.roomapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val beerViewModel by viewModels<MainViewModel>()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        beerViewModel.beersLiveData.observe(this){
            if (it != null) {
                binding.rvMain.layoutManager = LinearLayoutManager(this)
                binding.rvMain.adapter = BeerItemAdapter(this, it.beersList)
            }
        }
    }
}