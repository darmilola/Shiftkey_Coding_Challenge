package com.shiftkey.codingchallenge.domain.usecase

import com.shiftkey.codingchallenge.domain.model.ShiftDataModel
import com.shiftkey.codingchallenge.domain.repository.ShiftRepository
import com.shiftkey.codingchallenge.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetShiftUseCase @Inject constructor(
    private val repository: ShiftRepository
) : SingleUseCase<ShiftDataModel>() {

    private var address: String? = null
    private var type: String? = null
    private var startDate: String? = null
    private var endDate: String? = null


    fun provideParameters(address: String?, type: String?, startDate: String?, endDate: String?){
        this.address = address
        this.type = type
        this.startDate = startDate
        this.endDate = endDate
    }

    override fun buildUseCaseSingle(): Single<ShiftDataModel> {
        return repository.getShifts(address,type, startDate, endDate)
    }
}