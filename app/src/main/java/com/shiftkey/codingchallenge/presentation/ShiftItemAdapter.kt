package com.shiftkey.codingchallenge.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.shiftkey.codingchallenge.R

class ShiftItemAdapter (
    private val shiftList: ArrayList<ShiftModel>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_HEADER = 1
    private val TYPE_ITEM = 2


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == TYPE_HEADER){
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.shift_display_item_header, parent, false)
            return HeaderViewHolder(view)
        }
        else{

            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.shift_display_item, parent, false)
            return DataViewHolder(view)

        }

    }

    override fun getItemCount(): Int = shiftList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {}
        //holder.bind(moviesList[position])


    override fun getItemViewType(position: Int): Int {
        return if (shiftList[position].type == 1) {
            TYPE_HEADER
        }
        else{
            TYPE_ITEM
        }
    }


    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var navController: NavController? = null
                init {
                        itemView.setOnClickListener {
                        navController = Navigation.findNavController(itemView)
                        navController!!.navigate(R.id.action_shiftDisplay_to_shiftDetails)
                    }
                }

        }

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}