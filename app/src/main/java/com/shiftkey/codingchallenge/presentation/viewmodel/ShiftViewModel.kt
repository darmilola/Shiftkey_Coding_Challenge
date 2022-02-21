package com.shiftkey.codingchallenge.presentation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shiftkey.codingchallenge.domain.model.ShiftDataModel
import com.shiftkey.codingchallenge.domain.usecase.GetShiftUseCase
import com.shiftkey.codingchallenge.utils.NetworkUtils
import com.shiftkey.codingchallenge.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShiftViewModel @Inject constructor(private val getShiftUseCase: GetShiftUseCase,
                                         application: Application
): AndroidViewModel(application) {
    val receivedShiftsLiveData = MutableLiveData<Resource<ShiftDataModel>>()
    private val context = getApplication<Application>().applicationContext


    fun loadShifts(address: String?, type: String?, startDate: String?) {

        if(!NetworkUtils.isNetworkAvailable(context)){
            receivedShiftsLiveData.value = Resource.no_network(null)
            return
        }

        getShiftUseCase.provideParameters(address,type,startDate)
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

    fun loadNextWeekShifts(address: String?, type: String?, startDate: String?) {
        if(!NetworkUtils.isNetworkAvailable(context)){
            receivedShiftsLiveData.value = Resource.no_network(null)
            return
        }
        getShiftUseCase.provideParameters(address,type,startDate)
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