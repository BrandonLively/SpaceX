package com.wkdrabbit.space_x.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.wkdrabbit.space_x.R
import com.wkdrabbit.space_x.ui.models.Images
import com.wkdrabbit.space_x.ui.models.Launch
import com.wkdrabbit.space_x.ui.models.LaunchSite
import com.wkdrabbit.space_x.ui.models.Rocket
import kotlinx.android.synthetic.main.launch_list_item.view.*
import java.util.*
import java.text.SimpleDateFormat






class LaunchAdapter(var clickListener: OnLaunchClickListener): RecyclerView.Adapter<LaunchAdapter.LaunchViewHolder>(){

    var itemList: List<Launch> = listOf(Launch(0, "", Rocket(""), LaunchSite(""), 0, Images("")))


    private var listener: AdapterView.OnItemClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        return LaunchViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.launch_list_item, parent, false)
        )
    }

    fun submitList(list: List<Launch>){
        itemList = list
        this.notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        holder.initalize(itemList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class LaunchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun initalize(item: Launch, action: OnLaunchClickListener){
            itemView.setOnClickListener{action.onLaunchClick(item, adapterPosition)}

            val date = Date(item.launchDate * 1000L)
            val format = SimpleDateFormat("dd/MM/yyyy", Locale.US)
            format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"))
            val formattedDate = format.format(date)
            itemView.tv_launch_date.text  = "Date \n${formattedDate}"
            itemView.tv_mission_name.text = "Mission \n${item.missionName}"
            itemView.tv_rocket_name.text  = "Rocket \n${item.rocket.name}"
            itemView.tv_site_name.text  = "Site \n${item.launchSite.name}"
            if (item.images.imageUrl != ""){
                Picasso.get().load(item.images.imageUrl).into(itemView.iv_launch)
            }
        }

    }

    interface OnLaunchClickListener {
        fun onLaunchClick(item: Launch,position: Int)
    }
}