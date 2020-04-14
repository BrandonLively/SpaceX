package com.wkdrabbit.space_x.ui.screens

import android.content.Context
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

class LaunchListFragment: Fragment(){
    lateinit var         launchViewModel: LaunchViewModel
    lateinit var         launchAdapter: LaunchAdapter
    private lateinit var launchClickListener: LaunchAdapter.OnLaunchClickListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_launch_list, container, false)
    }

    override fun onAttach(context: Context) {
        //checks to see if context is from an implementation of the callback method
        //shouldn't be required
        if (context is LaunchAdapter.OnLaunchClickListener){
            launchClickListener = context
            launchAdapter = LaunchAdapter(launchClickListener)
        }
        super.onAttach(context)
    }

    private fun initRV(){
        rv_parent.apply{
            layoutManager = LinearLayoutManager(activity)
            adapter = launchAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        (activity!! as MainActivity)
            .setActionBarTitle("Space X - Historic Launches")
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRV()
        launchViewModel = ViewModelProvider(activity!!).get(LaunchViewModel::class.java)
        //Sets up observer for launchList
        launchViewModel.launchList.observe(activity!!, Observer {
            launchAdapter.submitList(it)
            launchAdapter.notifyDataSetChanged()
        })
    }

}
