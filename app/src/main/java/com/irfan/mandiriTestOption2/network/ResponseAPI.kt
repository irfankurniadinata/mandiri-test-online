package com.irfankurnia.test_sequis.network

import com.google.gson.annotations.SerializedName

data class ResponseAPI<T>(
    @SerializedName("status")
    var status: Int = 200,
    @SerializedName("success")
    var success: Boolean = false,
    @SerializedName("data")
    var data: T? = null,
    @SerializedName("error")
    var error: Error? = null
)