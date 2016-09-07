package com.example.alexander.recycleviewwithdownloadphotos

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import java.util.*
/**
 * Created by Alexander on 22.08.2016.
 */
class DataAdapter(private val context: Context, private val android_versions: ArrayList<PhotoData>) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): DataAdapter.ViewHolder {
        val view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {

        viewHolder.tv_android.setTextSize(20.0f)
        viewHolder.tv_android.setText(android_versions[i].androidVersionName)
        Picasso.with(context).load(android_versions[i].androidImageUrl).resize(300, 200).into(viewHolder.img_android)
    }

    override fun getItemCount(): Int {
        return android_versions.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal var tv_android: TextView
        internal var img_android: ImageView

        init {

            tv_android = view.findViewById(R.id.person_name) as TextView
            img_android = view.findViewById(R.id.person_photo) as ImageView
        }
    }
}