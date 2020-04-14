package com.wkdrabbit.space_x.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.wkdrabbit.space_x.R
import com.wkdrabbit.space_x.ui.viewmodels.LaunchViewModel
import kotlinx.android.synthetic.main.fragment_launch_details.*

// TODO: Rename parameter arguments, choose names that match


class LaunchDetailsFragment : Fragment() {

    lateinit var launchViewModel: LaunchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_launch_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        launchViewModel = ViewModelProvider(activity!!).get(LaunchViewModel::class.java)
        //Sets the Details layout to display the launch data
        launchViewModel.currentLaunch.observe(activity!!, Observer {
            if(iv_details_launch_patch != null) {
                if(it.images.imageUrl != null && it.images.imageUrl != "") {
                    Picasso.get().load(it.images.imageUrl).into(iv_details_launch_patch)
                }
                else{
                    iv_details_launch_patch.setImageDrawable(iv_details_launch_patch.resources.getDrawable(R.drawable.space_badge_placeholder))
                }
                tv_details.text = it.toString()
            }
            })
    }

}

