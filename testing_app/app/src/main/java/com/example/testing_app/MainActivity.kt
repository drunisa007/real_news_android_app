package com.example.testing_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.testing_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


   private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this)[CountViewModel::class.java]

        binding.counter.text = viewModel.count.toString()

        binding.buttonCounterPlus.setOnClickListener {
            viewModel.increaseCount()
            binding.counter.text = viewModel.count.toString()
        }

        binding.buttonCounterMinus.setOnClickListener {
            viewModel.decreaseCount()
            binding.counter.text = viewModel.count.toString()
        }




    }
}