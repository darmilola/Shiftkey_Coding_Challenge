package com.shiftkey.codingchallenge.domain.repository

import com.shiftkey.codingchallenge.domain.model.ShiftDataModel
import io.reactivex.Single

interface ShiftRepository {
    fun getShifts(address: String?, type: String?, startDate: String?): Single<ShiftDataModel>
}