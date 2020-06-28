package com.shamanth.qrcodescanner.views

import android.content.*
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.shamanth.qrcodescanner.utility.Constants
import com.shamanth.qrcodescanner.R
import com.shamanth.qrcodescanner.view_model.DetailViewModel


class BarcodeDetailActivity : AppCompatActivity() {
    private var detail: String? = null
    private lateinit var openUrl: Button
    private lateinit var openEmail: Button
    private lateinit var shareContent: Button
    private lateinit var shareApp: Button
    private lateinit var content: TextView
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_page)
        detail = intent.extras?.getString(Constants.VALUE)
        initViews()
        initViewModel()
//        handleData()
    }

    private fun initViewModel(){
        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        detailViewModel.currentName.value = detail
        detailViewModel.currentName.observe(this, Observer {
            content.text = it
        })
    }

    private fun initViews() {
        openUrl = findViewById(R.id.openUrl)
        openEmail = findViewById(R.id.send_email)
        shareContent = findViewById(R.id.share_content)
        shareApp = findViewById(R.id.share_app)
        content = findViewById(R.id.content)

        content.setOnClickListener { _-> copyToClipBoard() }
        openUrl.setOnClickListener { _-> openBrowser() }
        shareContent.setOnClickListener { _-> shareContent() }
        shareApp.setOnClickListener { _->
            Toast.makeText(this,"Under Development",Toast.LENGTH_SHORT).show()
        }


    }

//    private fun handleData() {
//        content.text = detail ?: "Scan Again"
//    }

    private fun shareContent() {
        if (detail == null)
            return
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_SUBJECT, "Barcode Scanner result")
            putExtra(Intent.EXTRA_TEXT, detail)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    private fun openBrowser() {
        if (detail?.startsWith("http://")!! && detail?.startsWith("https://")!!)
            detail = "http://$detail"

        try {
            val webpage: Uri = Uri.parse(detail)
            val myIntent = Intent(Intent.ACTION_VIEW, webpage)
            startActivity(myIntent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                this,
                "No application can handle this request. Please install a web browser or check your URL.",
                Toast.LENGTH_LONG
            ).show()
            e.printStackTrace()
        }
    }

    private fun copyToClipBoard() {
        val clipMan: ClipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("text label", detail)
        clipMan.setPrimaryClip(clip)
        Toast.makeText(
            this,
            "Text Copied to ClipBoard",
            Toast.LENGTH_LONG
        ).show()
    }
}