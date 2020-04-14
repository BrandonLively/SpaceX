package com.wkdrabbit.space_x.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wkdrabbit.space_x.data.SpaceXApiService
import com.wkdrabbit.space_x.ui.models.Launch
import kotlinx.coroutines.*

class LaunchViewModel: ViewModel() {
    var currentLaunch = MutableLiveData<Launch>(Launch())
    var launchList = MutableLiveData<List<Launch>>(listOf(Launch()))

    init {
        setLaunchList()
        currentLaunch.postValue(launchList.value?.get(0))
    }

    private fun setLaunchList(){
        val spaceXApiService = SpaceXApiService()

        GlobalScope.launch(Dispatchers.Unconfined){
            //Retrieves and Sorts launchList
            launchList.postValue(spaceXApiService.getLaunchesAsync().await().sortedBy{it.launchDate}.reversed())
        }
    }

}