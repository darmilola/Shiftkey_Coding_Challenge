package com.shiftkey.codingchallenge.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shiftkey.codingchallenge.domain.model.ShiftDataModel
import com.shiftkey.codingchallenge.domain.usecase.GetShiftUseCase
import com.shiftkey.codingchallenge.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShiftViewModel @Inject constructor(private val getShiftUseCase: GetShiftUseCase) :
    ViewModel() {
    val receivedShiftsLiveData = MutableLiveData<Resource<ShiftDataModel>>()
    //@Inject
    // var isNetworkAvailable: Boolean = false

    fun loadShifts(address: String?, type: String?, startDate: String?, endDate: String?) {

     /*   if(!isNetworkAvailable){
            receivedShiftsLiveData.value = Resource.error("Network not Available",null)
            return
        }*/

        getShiftUseCase.provideParameters(address,type,startDate,endDate)
        receivedShiftsLiveData.value = Resource.loading(null)
        getShiftUseCase.execute(
            onSuccess = {
                receivedShiftsLiveData.value = Resource.success(it)
            },
            onError = {
                receivedShiftsLiveData.value = Resource.error(it.localizedMessage,null)
                it.printStackTrace()
            }
        )
    }

}