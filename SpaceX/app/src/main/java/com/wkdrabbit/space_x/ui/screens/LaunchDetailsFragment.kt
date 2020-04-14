package com.wkdrabbit.space_x.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.wkdrabbit.space_x.R
import com.wkdrabbit.space_x.ui.viewmodels.LaunchViewModel
import kotlinx.android.synthetic.main.fragment_launch_details.*

class LaunchDetailsFragment : Fragment() {

    private lateinit var launchViewModel: LaunchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_launch_details, container, false)
    }

    override fun onResume() {
        super.onResume()
      updateTitle(launchViewModel.currentLaunch.value!!.missionName)
    }
    private fun updateTitle(title:String){
        (activity!! as MainActivity)
            .setActionBarTitle(title)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launchViewModel = ViewModelProvider(activity!!).get(LaunchViewModel::class.java)
        //Sets the Details layout to display the launch data
        launchViewModel.currentLaunch.observe(activity!!, Observer {
            if(iv_details_launch_patch != null) {
                //updates title on current mission change
                updateTitle(it.missionName)
                if(it.links.imageUrl != "" && it.links.imageUrl != null) {
                    Picasso.get().load(it.links.imageUrl).into(iv_details_launch_patch)
                }
                else{
                    iv_details_launch_patch.setImageDrawable(
                        ContextCompat.getDrawable(activity!!, R.drawable.space_badge_placeholder))
                }
                // uses overridden toString method to set the details of the launch to the textView
                tv_details.text = it.toString()
            }
        })
    }


}

