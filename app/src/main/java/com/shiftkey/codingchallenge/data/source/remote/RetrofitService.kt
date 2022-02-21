package com.shiftkey.codingchallenge.data.source.remote

import com.shiftkey.codingchallenge.domain.model.ShiftDataModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {
    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("available_shifts")
    fun getShifts(@Query("address") address: String, @Query("type") type: String, @Query("start") startDate: String): Single<ShiftDataModel>
}