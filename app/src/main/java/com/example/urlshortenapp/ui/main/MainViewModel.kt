package com.example.urlshortenapp.ui.main

import android.util.Log
import androidx.lifecycle.*
import com.example.urlshortenapp.model.PostUrl
import com.example.urlshortenapp.retrofit.Webservice
import retrofit2.Call
import retrofit2.Response

class MainViewModel : ViewModel() {

    var urlLiveData: MutableLiveData<PostUrl>? = null

    fun getUser(): MutableLiveData<PostUrl> {

        if (urlLiveData == null) {
            urlLiveData = MutableLiveData<PostUrl>()

        }
        return urlLiveData as MutableLiveData<PostUrl>

    }

    fun getmainUrl(user: PostUrl) {

        val apiclass = Webservice.create()

        val call = apiclass.geturl(user)

        call.enqueue(object : retrofit2.Callback<PostUrl> {

            override fun onResponse(call: Call<PostUrl>, response: Response<PostUrl>) {

                Log.i("", "post submitted to API." + response.body()?.toString())

                if (response.isSuccessful) {
                    urlLiveData?.value = response.body()
                    Log.i("", "post registration to API" + response.body()?.toString())
                }
            }

            override fun onFailure(call: Call<PostUrl>, t: Throwable) {

                // Toast.makeText(applicationContext, "Not Successful.....", Toast.LENGTH_SHORT).show()

            }
        })


    }

}