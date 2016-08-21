package com.example.alexander.recycleviewwithdownloadphotos

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by Alexander on 22.08.2016.
 */
abstract class RVAdapter internal constructor(internal var persons: List<PhotoData>) : RecyclerView.Adapter<RVAdapter.PersonViewHolder>() {

    open class PersonViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var cv: CardView
        internal var personName: TextView
        internal var personPhoto: ImageView

        init {
            cv = itemView.findViewById(R.id.cv) as CardView
            personName = itemView.findViewById(R.id.person_name) as TextView
            personPhoto = itemView.findViewById(R.id.person_photo) as ImageView
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): PersonViewHolder {
        val v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false)
        val pvh = PersonViewHolder(v)
        return pvh
    }

    override fun onBindViewHolder(personViewHolder: PersonViewHolder, i: Int) {
    }
}