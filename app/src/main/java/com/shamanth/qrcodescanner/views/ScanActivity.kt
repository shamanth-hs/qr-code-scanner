package com.shamanth.qrcodescanner.views

import android.content.Intent
import android.os.Bundle
import android.util.SparseArray
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.vision.barcode.Barcode
import com.shamanth.qrcodescanner.utility.Constants
import com.shamanth.qrcodescanner.R
import info.androidhive.barcode.BarcodeReader
import info.androidhive.barcode.BarcodeReader.BarcodeReaderListener

class ScanActivity : AppCompatActivity(), BarcodeReaderListener {
    private var barcodeReader: BarcodeReader? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)
        barcodeReader = supportFragmentManager.findFragmentById(R.id.barcode_scanner) as BarcodeReader?
    }

    override fun onScanned(barcode: Barcode) {

        // playing barcode reader beep sound
        barcodeReader!!.playBeep()

        // ticket details activity by passing barcode
        val intent = Intent(this@ScanActivity, BarcodeDetailActivity::class.java)
        intent.putExtra(Constants.VALUE, barcode.displayValue)
        startActivity(intent)
    }

    override fun onScannedMultiple(barcodes: List<Barcode>) {}
    override fun onBitmapScanned(sparseArray: SparseArray<Barcode>) {}
    override fun onScanError(errorMessage: String) {}
    override fun onCameraPermissionDenied() {
        finish()
    }
}