package com.shiftkey.codingchallenge.domain.model

import com.google.gson.annotations.SerializedName

data class LocalizedSpecialtyModel(
    @field:SerializedName("id") val id: Int?,
    @field:SerializedName("specialty_id") val specialityId: Int?,
    @field:SerializedName("name") val name: String?,
    @field:SerializedName("abbreviation") val abbreviation: String?,
    @field:SerializedName("specialty") val specialty: SpecialtyModel?)  {
}