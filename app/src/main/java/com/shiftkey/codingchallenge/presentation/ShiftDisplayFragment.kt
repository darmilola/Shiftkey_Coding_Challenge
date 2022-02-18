package com.shiftkey.codingchallenge.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shiftkey.codingchallenge.R
import com.shiftkey.codingchallenge.utils.HeaderItemDecoration


class ShiftDisplayFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ShiftItemAdapter
    lateinit var layoutManager: LinearLayoutManager
    val arrayList = ArrayList<ShiftModel>()
    lateinit var mView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        mView = inflater.inflate(R.layout.fragment_shift_display, container, false)
        initView()
        return mView
    }

    private fun initView(){
        recyclerView = mView.findViewById(R.id.shift_item_recyclerview)
        arrayList.add(ShiftModel(1))
        arrayList.add(ShiftModel(2))
        arrayList.add(ShiftModel(2))
        arrayList.add(ShiftModel(2))
        arrayList.add(ShiftModel(2))
        arrayList.add(ShiftModel(2))
        arrayList.add(ShiftModel(2))
        arrayList.add(ShiftModel(1))
        arrayList.add(ShiftModel(2))
        arrayList.add(ShiftModel(2))
        arrayList.add(ShiftModel(2))
        arrayList.add(ShiftModel(2))
        arrayList.add(ShiftModel(2))
        arrayList.add(ShiftModel(2))
        arrayList.add(ShiftModel(1))
        arrayList.add(ShiftModel(2))
        arrayList.add(ShiftModel(2))
        arrayList.add(ShiftModel(2))
        arrayList.add(ShiftModel(2))
        arrayList.add(ShiftModel(2))
        arrayList.add(ShiftModel(2))
        arrayList.add(ShiftModel(1))
        arrayList.add(ShiftModel(2))
        arrayList.add(ShiftModel(2))
        arrayList.add(ShiftModel(2))
        arrayList.add(ShiftModel(2))
        arrayList.add(ShiftModel(2))
        arrayList.add(ShiftModel(2))

        adapter = ShiftItemAdapter(arrayList)
        layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                layoutManager.orientation
            )
        )

        recyclerView.apply {
            // adapter = this@MainActivity.adapter
            addItemDecoration(
                HeaderItemDecoration(this) { itemPosition ->
                    this@ShiftDisplayFragment.adapter.getItemViewType(itemPosition) == 1
                }
            )
        }
        adapter.notifyDataSetChanged()
    }



}