 package com.example.memeshare

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.chooser.ChooserTarget
import android.view.View
import androidx.annotation.RequiresApi
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_main.*

 class MainActivity : AppCompatActivity() {
     var currentUrl: String? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadMeme()
    }
     private fun loadMeme(){
         progressBar.visibility=View.VISIBLE
         val queue = Volley.newRequestQueue(this)
         val url = "https://meme-api.herokuapp.com/gimme"

// Request a string response from the provided URL.

         val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
             { response ->
                 progressBar.visibility=View.GONE
                  currentUrl=response.getString("url")

            Glide.with(this).load(currentUrl)
             //                 listener(object:RequestListener<Drawable> {
//                     override fun onLoadFailed(
//                         e: GlideException?,
//                         model: Any?,
//                         target: Target<Drawable>?,
//                         isFirstResource: Boolean
//                     ): Boolean {
//                         progressBar.visibility=View.GONE
//                         return false
//                     }
//
//                     override fun onResourceReady(
//                         resource: Drawable?,
//                         model: Any?,
//                         target: Target<Drawable>?,
//                         dataSource: DataSource?,
//                         isFirstResource: Boolean
//                     ): Boolean {
//                         TODO("Not yet implemented")
//                         progressBar.visibility=View.GONE
//                         return false
//                     }

//                 })
             .into(imageView)


             },
             { error ->
                 // TODO: Handle error
             }
         )

// Add the request to the RequestQueue.
         queue.add(jsonObjectRequest)
     }



     fun shareMeme(view: View) {
         val intent =Intent(Intent.ACTION_SEND)
         intent.type="text/plain"
         val chooser=Intent.createChooser(intent,"Share the meme")
         startActivity(chooser)
     }
     fun nextMeme(view: View) {
         loadMeme()
     }
 }