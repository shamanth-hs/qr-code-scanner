package com.shamanth.qrcodescanner.utility

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AlertDialog

object CommonMethods {
    private fun isOnline(context: Context): Boolean{
        val connectivityManager : ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if(connectivityManager.getNetworkInfo(0)?.state == NetworkInfo.State.CONNECTED ||
            connectivityManager.getNetworkInfo(1)?.state == NetworkInfo.State.CONNECTED){
                return true;
        }
        return false
    }

    fun checkInternet(context: Context):Boolean{
        if(!isOnline(context)){
            showInternetDialog(context)
            return false
        }
        return true
    }

    private fun showInternetDialog(context: Context){
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.apply {
            setCancelable(false)
            setTitle("No Internet")
            setMessage("It seems You have disconnected from internet")
            setPositiveButton("Enable Internet") { dialogInterface: DialogInterface, i: Int ->
                enableInternet(context)
            }
            setNegativeButton("Okay") { dialogInterface: DialogInterface, i: Int ->
            }

        }
        alertDialog.show()
    }

    private fun enableInternet(context: Context){
        context.startActivity(Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS))
    }
}