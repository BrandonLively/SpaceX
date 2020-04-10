package com.wkdrabbit.space_x

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.wkdrabbit.space_x.domain.SpaceXRepository
import com.wkdrabbit.space_x.domain.SpaceXRepositoryProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = SpaceXRepositoryProvider.provideSearchRepository()

        compositeDisposable.add(
            repository.getLaunches().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe ({
                        result ->
                    Log.d("Launches", result.size.toString())
                }, { error ->
                    error.printStackTrace()
                })
        )
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}
