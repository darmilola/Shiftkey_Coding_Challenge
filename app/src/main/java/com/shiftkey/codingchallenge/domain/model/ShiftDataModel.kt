package com.shiftkey.codingchallenge.domain.model

import com.google.gson.annotations.SerializedName

data class ShiftDataModel(
    @field:SerializedName("data") val shiftData: ArrayList<AvailableShiftsModel>,
    @field:SerializedName("meta") val meta: MetaModel){
}