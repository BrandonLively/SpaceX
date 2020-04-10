package com.wkdrabbit.space_x.domain

import com.wkdrabbit.space_x.data.SpaceXApiService
import com.wkdrabbit.space_x.ui.models.Launch
import io.reactivex.Observable

class SpaceXRepository(private val spaceXApiService: SpaceXApiService) {
    fun getLaunches(): Observable<List<Launch>> {
        return spaceXApiService.getLaunches()
    }
}

object SpaceXRepositoryProvider{
    fun provideSearchRepository(): SpaceXRepository{
        return SpaceXRepository(SpaceXApiService.create())
    }
}