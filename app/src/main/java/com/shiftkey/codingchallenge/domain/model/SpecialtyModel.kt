package com.shiftkey.codingchallenge.domain.model

import com.google.gson.annotations.SerializedName

data class SpecialtyModel(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("name") val name: String?,
    @field:SerializedName("color") val color: String?,
    @field:SerializedName("abbreviation") val abbreviation: String?) {
}