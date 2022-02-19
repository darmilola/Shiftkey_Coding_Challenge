package com.shiftkey.codingchallenge.data.repository

import com.shiftkey.codingchallenge.data.source.remote.RetrofitService
import com.shiftkey.codingchallenge.domain.model.ShiftDataModel
import com.shiftkey.codingchallenge.domain.repository.ShiftRepository
import io.reactivex.Single

class ShiftRepositoryImpl(
    private val retrofitService: RetrofitService
) : ShiftRepository {
    override fun getShifts(address: String?, type: String?, startDate: String?, endDate: String?): Single<ShiftDataModel> {
        return retrofitService.getShifts(address!!,type!!,startDate!!,endDate!!)
    }
}