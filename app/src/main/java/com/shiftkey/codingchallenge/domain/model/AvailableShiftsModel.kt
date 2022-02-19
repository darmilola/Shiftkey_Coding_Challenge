package com.shiftkey.codingchallenge.domain.model

import com.google.gson.annotations.SerializedName

class AvailableShiftsModel(
    @field:SerializedName("date") val shiftStartDate: String?,
    @field:SerializedName("shifts") val shiftList: ArrayList<ShiftModel>?)  {
}