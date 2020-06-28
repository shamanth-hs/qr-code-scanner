package com.shamanth.qrcodescanner.app

import androidx.multidex.MultiDexApplication

class MyApplication : MultiDexApplication(){

    private lateinit var mInstance: MyApplication;

    override fun onCreate() {
        super.onCreate()
        mInstance = this;
    }


}