package com.shiftkey.codingchallenge.data.source.remote

import com.shiftkey.codingchallenge.domain.model.ShiftDataModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {
    @GET("available_shifts")
    fun getShifts(@Query("address") address: String, @Query("type") type: String, @Query("start") startDate: String, @Query("end") endDate: String): Single<ShiftDataModel>
}