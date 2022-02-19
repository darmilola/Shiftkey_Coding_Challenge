package com.shiftkey.codingchallenge.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.runtime.snapshots.Snapshot.Companion.observe
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.transition.platform.MaterialSharedAxis
import com.shiftkey.codingchallenge.R
import com.shiftkey.codingchallenge.domain.model.ShiftDataModel
import com.shiftkey.codingchallenge.domain.model.ShiftModel
import com.shiftkey.codingchallenge.presentation.adapter.ShiftItemAdapter
import com.shiftkey.codingchallenge.presentation.viewmodel.ShiftViewModel
import com.shiftkey.codingchallenge.utils.HeaderItemDecoration
import com.shiftkey.codingchallenge.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShiftDisplayFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ShiftItemAdapter
    lateinit var layoutManager: LinearLayoutManager
    val arrayList = ArrayList<ShiftModel>()
    lateinit var mView: View
    private val viewModel: ShiftViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
    }

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
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true)
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false)

        viewModel.loadShifts("Dallas,TX","list","2022-02-20","2022-02-25")


        viewModel.receivedShiftsLiveData.observe(
            viewLifecycleOwner,
            Observer {
                it?.let {
                    when (it.status) {
                        Status.SUCCESS -> {
                            Log.e("initView: ", it.data.toString())
                            it.data?.let {
                                    shiftDataModels : ShiftDataModel -> renderList(shiftDataModels)
                            }
                        }
                        Status.LOADING -> {
                            Log.e("initView: ", "Loading")
                        }
                        Status.ERROR -> {
                            Log.e("initView: ", it.message as String)
                        }

                    }

                }
            }
        )




    /*    recyclerView = mView.findViewById(R.id.shift_item_recyclerview)
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
        adapter.notifyDataSetChanged()*/
    }


    private fun renderList(shiftList: ShiftDataModel) {
        Log.e("initView: ", shiftList.shiftData[0].shiftList.toString()!!)
    }

}