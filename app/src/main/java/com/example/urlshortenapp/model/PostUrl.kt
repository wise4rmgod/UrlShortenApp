package com.example.urlshortenapp.model

import com.google.gson.annotations.SerializedName

class PostUrl(
    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("hashid")
    val hashid: String? = null


)