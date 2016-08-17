package com.example.alexander.downloadphoto

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by Alexander on 17.08.2016.
 */
open class RVAdapter : RecyclerView.Adapter<RVAdapter.PersonViewHolder> {

    open class PersonViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var cv: CardView
        internal var personName: TextView
        internal var personAge: TextView
        internal var personPhoto: ImageView

        init {
            cv = itemView.findViewById(R.id.cv) as CardView
            personName = itemView.findViewById(R.id.person_name) as TextView
            personAge = itemView.findViewById(R.id.person_age) as TextView
            personPhoto = itemView.findViewById(R.id.person_photo) as ImageView
        }
    }
    private var persons: List<Person>? = null
    constructor(persons: List<Person>) {
        this.persons = persons
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun getItemCount(): Int {
        return persons!!.size
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): PersonViewHolder {// вызывается, когда кастомный ViewHolder должен быть инициализирован.
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.item, viewGroup, false)
        val pvh = PersonViewHolder(v)
        return pvh
    }

    override fun onBindViewHolder(personViewHolder: PersonViewHolder, i: Int) {// похож на метод getView
        personViewHolder.personName.text = persons!!.get(i).name
        personViewHolder.personAge.text = persons!!.get(i).age
        personViewHolder.personPhoto.setImageResource(persons!!.get(i).photoId)
    }

}