package com.wkdrabbit.space_x.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wkdrabbit.space_x.R
import com.wkdrabbit.space_x.ui.adapters.LaunchAdapter
import com.wkdrabbit.space_x.ui.viewmodels.LaunchViewModel
import kotlinx.android.synthetic.main.fragment_launch_list.*

class LaunchListFragment() : Fragment(){

    lateinit var launchViewModel: LaunchViewModel
    lateinit var launchAdapter: LaunchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_launch_list, container, false)
    }

    fun initRV(){
        rv_parent.apply{
            layoutManager = LinearLayoutManager(activity)
            adapter = launchAdapter
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRV()
        launchViewModel = ViewModelProvider(activity!!).get(LaunchViewModel::class.java)
        //Sets up observer for launchList
        launchViewModel.launchList.observe(activity!!, Observer {
            launchAdapter.submitList(it)
            launchAdapter.notifyDataSetChanged()
        })
    }

    companion object{
        fun newInstance(launchClickListener: LaunchAdapter.OnLaunchClickListener) = LaunchListFragment().apply {
            launchAdapter = LaunchAdapter(launchClickListener)
        }
    }
}
