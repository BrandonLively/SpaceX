package com.wkdrabbit.space_x.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.wkdrabbit.space_x.R

// TODO: Rename parameter arguments, choose names that match


class LaunchDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_launch_details, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(): LaunchDetailsFragment{
            val fragment = LaunchDetailsFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
}
}
