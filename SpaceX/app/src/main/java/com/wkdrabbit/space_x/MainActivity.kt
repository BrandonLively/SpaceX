package com.wkdrabbit.space_x

import android.app.LauncherActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.wkdrabbit.space_x.domain.SpaceXRepository
import com.wkdrabbit.space_x.domain.SpaceXRepositoryProvider
import com.wkdrabbit.space_x.ui.adapters.LaunchAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private lateinit var launchAdapter: LaunchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerViewInit()
        repoInit()
    }

    fun repoInit(){
        val repository = SpaceXRepositoryProvider.provideSearchRepository()


        compositeDisposable.add(
            repository.getLaunches().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe ({
                        result ->
                    launchAdapter.submitList(result)
                }, { error ->
                    error.printStackTrace()
                })
        )
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    fun recyclerViewInit(){
        rv_parent.apply{
            layoutManager = LinearLayoutManager(this@MainActivity)
            launchAdapter = LaunchAdapter()
            adapter = launchAdapter
        }
    }
}
