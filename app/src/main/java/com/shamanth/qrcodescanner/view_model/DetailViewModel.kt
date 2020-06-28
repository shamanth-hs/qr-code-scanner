package com.shamanth.qrcodescanner.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailViewModel :ViewModel(){
    val currentName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}