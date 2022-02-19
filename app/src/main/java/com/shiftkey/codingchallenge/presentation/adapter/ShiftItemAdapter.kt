package com.shiftkey.codingchallenge.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.shiftkey.codingchallenge.R
import com.shiftkey.codingchallenge.domain.model.ShiftModel
import com.shiftkey.codingchallenge.utils.Constants

class ShiftItemAdapter (
    private val shiftList: ArrayList<ShiftModel>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == Constants.SHIFT_ITEM_TYPE_HEADER){
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
        return if (shiftList[position].viewType == Constants.SHIFT_ITEM_TYPE_HEADER) {
            Constants.SHIFT_ITEM_TYPE_HEADER
        }
        else{
            Constants.SHIFT_ITEM_TYPE_ITEM
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