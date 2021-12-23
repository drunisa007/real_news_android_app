package com.example.testing_app

import androidx.lifecycle.ViewModel

class CountViewModel : ViewModel() {

    var count : Int = 0

    fun increaseCount(){
        count++
    }

    fun decreaseCount(){
        count--
    }
}