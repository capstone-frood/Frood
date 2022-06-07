package com.frood.app.api

import com.google.gson.annotations.SerializedName

data class PredictResponse(

    @field:SerializedName("Status")
    val status: String,

    @field:SerializedName("Note")
    val note: String,

    @field:SerializedName("Expired")
    val expired: String
)
