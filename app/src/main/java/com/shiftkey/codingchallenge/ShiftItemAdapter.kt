package com.shiftkey.codingchallenge

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ShiftItemAdapter (
    private val moviesList: ArrayList<String>
) : RecyclerView.Adapter<ShiftItemAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.shift_display_item, parent,
                false
            )
        )

    override fun getItemCount(): Int = moviesList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {}
        //holder.bind(moviesList[position])





    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        }
    }
