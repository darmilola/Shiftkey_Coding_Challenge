package com.shiftkey.codingchallenge.domain.model

import com.google.gson.annotations.SerializedName

class MetaModel(
    @field:SerializedName("lat") val lat: String,
    @field:SerializedName("lng") val lng: String) {
}