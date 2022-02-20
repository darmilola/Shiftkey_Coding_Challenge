package com.shiftkey.codingchallenge.domain.model

import com.google.gson.annotations.SerializedName
import com.shiftkey.codingchallenge.utils.Constants

data class AvailableShiftsModel(
    @field:SerializedName("date") val shiftStartDate: String?,
    @field:SerializedName("shifts") val shiftList: MutableList<ShiftModel>?)  {

}