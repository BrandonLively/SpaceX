package com.wkdrabbit.space_x.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wkdrabbit.space_x.data.SpaceXApiService
import com.wkdrabbit.space_x.ui.models.Images
import com.wkdrabbit.space_x.ui.models.Launch
import com.wkdrabbit.space_x.ui.models.LaunchSite
import com.wkdrabbit.space_x.ui.models.Rocket
import kotlinx.coroutines.*

class LaunchViewModel(): ViewModel() {
    var blankLaunch = Launch(0, "", Rocket(""), LaunchSite(""), 0, Images(""))
    var currentLaunch = MutableLiveData<Launch>(blankLaunch)
    var launchList = MutableLiveData<List<Launch>>(listOf(blankLaunch))

    init {
        setLaunchList()
        currentLaunch.postValue(launchList.value?.get(0))
    }

    fun setLaunchList(){
        val spaceXApiService = SpaceXApiService()

        GlobalScope.launch(Dispatchers.Unconfined){
            //Retrieves and Sorts launchList
            launchList.postValue(spaceXApiService.getLaunchesAsync().await().sortedBy{it.launchDate}.reversed())
        }
    }

}