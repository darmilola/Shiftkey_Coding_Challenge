package com.shiftkey.codingchallenge.presentation.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.shiftkey.codingchallenge.R
import com.shiftkey.codingchallenge.databinding.ShiftDisplayItemBinding
import com.shiftkey.codingchallenge.databinding.ShiftDisplayItemHeaderBinding
import com.shiftkey.codingchallenge.databinding.ShiftItemLoadingBinding
import com.shiftkey.codingchallenge.domain.model.ShiftModel
import com.shiftkey.codingchallenge.utils.Constants
import com.shiftkey.codingchallenge.utils.DateUtil


class ShiftItemAdapter (
    private val shiftList: MutableList<ShiftModel>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == Constants.SHIFT_ITEM_TYPE_HEADER){
            val headerBinding = ShiftDisplayItemHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return HeaderViewHolder(headerBinding)
        }
        else if(viewType == Constants.SHIFT_ITEM_TYPE_LOADING){
            val loadingBinding = ShiftItemLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return LoadingViewHolder(loadingBinding)
        }
        else{
            val itemBinding = ShiftDisplayItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return DataViewHolder(itemBinding)
        }

    }

    override fun getItemCount(): Int = shiftList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val shiftModel = shiftList[position]
        val currentDisplayType = shiftModel.viewType
        if (currentDisplayType == Constants.SHIFT_ITEM_TYPE_LOADING) {

        } else if (currentDisplayType == Constants.SHIFT_ITEM_TYPE_HEADER) {
            val mHolder = holder as HeaderViewHolder
            mHolder.bind(shiftModel)
        } else {
            val mHolder = holder as DataViewHolder
            mHolder.bind(shiftModel)
            mHolder.itemView.setOnClickListener {
                var navController: NavController = Navigation.findNavController(mHolder.itemView)
                val bundle = Bundle()
                bundle.putSerializable("shiftDetail",shiftModel)
                navController!!.navigate(R.id.action_shiftDisplay_to_shiftDetails, bundle)
            }
        }
    }


    fun addListData(mShiftList: MutableList<ShiftModel>) {
        shiftList.addAll(mShiftList)
        notifyDataSetChanged()
    }

    fun addData(shiftModel: ShiftModel) {
        shiftList.add(shiftModel)
        notifyDataSetChanged()
    }

    fun removeData(shiftModel: ShiftModel){
        shiftList.remove(shiftModel)
        notifyDataSetChanged()

    }

    fun removeAllData(){
        shiftList.clear()
        notifyDataSetChanged()
    }

    fun removeLoader(){
        shiftList.removeAt(shiftList.size - 1)
        notifyDataSetChanged()

    }

    fun getDataList(): MutableList<ShiftModel>  {
        return shiftList;
    }


    override fun getItemViewType(position: Int): Int {
       if (shiftList[position].viewType == Constants.SHIFT_ITEM_TYPE_HEADER) {
           return Constants.SHIFT_ITEM_TYPE_HEADER
        }
        else if (shiftList[position].viewType == Constants.SHIFT_ITEM_TYPE_LOADING) {
           return Constants.SHIFT_ITEM_TYPE_LOADING
        }
        else{
           return Constants.SHIFT_ITEM_TYPE_ITEM
        }
    }


    class DataViewHolder(private val itemBinding: ShiftDisplayItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        var dateUtil: DateUtil = DateUtil()

        fun bind(shiftModel: ShiftModel) {
            itemBinding.shiftItemSpecialtyName.text = shiftModel.localizedSpecialty!!.name
            itemBinding.shiftItemStartDateTime.text = dateUtil.formatDateInLiteralFormat(shiftModel.normalizedStartDateTime!!)
            itemBinding.shiftItemIsPremium.visibility = if (shiftModel.isPremiumRate == true) View.VISIBLE else View.GONE
        }
    }



    class HeaderViewHolder(private val itemBinding: ShiftDisplayItemHeaderBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        var dateUtil: DateUtil = DateUtil()

        fun bind(shiftModel: ShiftModel) {
            val startDateTime = shiftModel.startDate
            val endDateTime = shiftModel.endDate
            val startDate = dateUtil.formatDateInLiteralFormat(dateUtil.extractDateFromString(startDateTime!!))
            val endDate = dateUtil.formatDateInLiteralFormat(dateUtil.extractDateFromString(endDateTime!!))
            val builder = StringBuilder()
            builder.append(startDate)
                .append(" - ")
                .append(endDate)
            itemBinding.shiftItemHeaderText.text = builder.toString()
        }
    }

    class LoadingViewHolder(private val itemBinding: ShiftItemLoadingBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    }

}