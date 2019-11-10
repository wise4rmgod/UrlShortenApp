package com.example.urlshortenapp.ui.main

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.urlshortenapp.R
import com.example.urlshortenapp.model.PostUrl
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    lateinit var myClipboard: ClipboardManager
    lateinit var myClip: ClipData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        myClipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        Snackbar.make(
            root,
            "Please add http:// or https:// before the typing your Url",
            Snackbar.LENGTH_SHORT
        ).show()
        urlbtn.setOnClickListener {
            val urlpost = PostUrl(enterurl.text.toString())
            viewModel.getmainUrl(urlpost)
        }

        viewModel.getUser().observe(this, Observer<PostUrl> { u ->
            shortened_text.text = "https://rel.ink/" + u.hashid

            copy_to_clipboard_btn.setOnClickListener {

                myClip = ClipData.newPlainText("copied text", "https://rel.ink/" + u.hashid)
                myClipboard.setPrimaryClip(myClip)
                Toast.makeText(
                    applicationContext, "Copied to ClipBoard",
                    Toast.LENGTH_SHORT
                ).show()

            }

        })


    }


}
