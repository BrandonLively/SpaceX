package com.wkdrabbit.space_x.ui.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.wkdrabbit.space_x.R
import com.wkdrabbit.space_x.ui.adapters.LaunchAdapter
import com.wkdrabbit.space_x.ui.models.Launch
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LaunchAdapter.OnLaunchClickListener{
    private lateinit var launchListFragment: LaunchListFragment
    private lateinit var launchDetailsFragment: LaunchDetailsFragment
    private var twoPane: Boolean = false


    //Sets up onClick callback functionality depending on which layout is being used
    override fun onLaunchClick(item: Launch, position: Int) {
        launchListFragment.launchAdapter.currentSelection = item.flightNumber
        launchListFragment.launchViewModel.currentLaunch.value = item
        if(twoPane){
            launchListFragment.launchAdapter.notifyDataSetChanged()
        }
        else{
            launchListFragment.launchViewModel.currentLaunch.value = item
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.list_container, LaunchDetailsFragment())
                .addToBackStack("Launch Details")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
        }
    }

    fun setActionBarTitle(title: String) {
        supportActionBar!!.title = title
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (details_container != null) {
            twoPane = true
        }

        //clears any fragments left behind upon app reload -- most likely not needed, but just incase
        for (fragment in supportFragmentManager.fragments) {
            supportFragmentManager.beginTransaction().remove(fragment).commit()
        }

        //Sets up fragments based on which layout is being used
        launchListFragment = LaunchListFragment()
        if (twoPane) {
            launchDetailsFragment = LaunchDetailsFragment()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.list_container, launchListFragment)
                .replace(R.id.details_container, launchDetailsFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
        }
        else{
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.list_container, launchListFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
        }
    }
}
