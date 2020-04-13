package com.wkdrabbit.space_x.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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




class LaunchAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var itemList: List<Launch> = listOf(Launch(0, "", Rocket(""), LaunchSite(""), 0, Images("")))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TransactionViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.launch_list_item, parent, false)
        )
    }

    fun submitList(list: List<Launch>){
        itemList = list
        this.notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is TransactionViewHolder -> {holder.bind(itemList[position])}
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class TransactionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var launchDate = itemView.tv_launch_date
        var missionName = itemView.tv_mission_name
        var rocketName = itemView.tv_rocket_name
        var launchSite = itemView.tv_site_name
        var patchImage = itemView.iv_launch

        fun bind(launch:Launch){
            val date = Date(launch.launchDate * 1000L)
            val format = SimpleDateFormat("dd/MM/yyyy", Locale.US)
            format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"))
            val formattedDate = format.format(date)
            launchDate.text  = "Date \n ${formattedDate}"
            missionName.text = "Mission \n${launch.missionName}"
            rocketName.text  = "Rocket \n${launch.rocket.name}"
            launchSite.text  = "Site \n${launch.launchSite.name}"
            if (launch.images.imageUrl != ""){
                Picasso.get().load(launch.images.imageUrl).into(patchImage)
            }
        }
    }
}