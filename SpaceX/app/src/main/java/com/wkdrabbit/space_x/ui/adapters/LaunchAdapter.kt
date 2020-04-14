package com.wkdrabbit.space_x.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.wkdrabbit.space_x.R
import com.wkdrabbit.space_x.formatEpochDateTime
import com.wkdrabbit.space_x.ui.models.Launch
import kotlinx.android.synthetic.main.launch_list_item.view.*

class LaunchAdapter(var clickListener: OnLaunchClickListener): RecyclerView.Adapter<LaunchAdapter.LaunchViewHolder>(){

    var itemList: List<Launch> = listOf(Launch())
    var currentSelection = 0L

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
        //Changes background if item is the one selected
        if (currentSelection == itemList[position].flightNumber){
            holder.itemView.background = ContextCompat.getDrawable(holder.itemView.context, R.color.colorSelectedCard)
        }
        else{
            holder.itemView.background = ContextCompat.getDrawable(holder.itemView.context, R.color.colorUnselectedCard)
        }
        holder.initialize(itemList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class LaunchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun initialize(item: Launch, action: OnLaunchClickListener){
            itemView.setOnClickListener{action.onLaunchClick(item, adapterPosition)}
            itemView.tv_launch_date.text  = "Date - ${formatEpochDateTime(item.launchDate)}"
            itemView.tv_mission_name.text = "Mission - ${item.missionName}"
            itemView.tv_rocket_name.text  = "Rocket - ${item.rocket.name}"
            itemView.tv_site_name.text  = "Site - ${item.launchSite.name}"
            //Sets Placeholder image if URL is null
            if (item.links.imageUrlSmall != "" && item.links.imageUrlSmall != null){
                Picasso.get().load(item.links.imageUrlSmall).into(itemView.iv_launch)
            }
            else{
                itemView.iv_launch.setImageDrawable(itemView.resources.getDrawable(R.drawable.space_badge_placeholder))
            }
        }
    }

    interface OnLaunchClickListener {
        fun onLaunchClick(item: Launch,position: Int)
    }
}