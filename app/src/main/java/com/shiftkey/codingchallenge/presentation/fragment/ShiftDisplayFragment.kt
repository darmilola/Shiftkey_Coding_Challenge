package com.shiftkey.codingchallenge.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.runtime.snapshots.Snapshot.Companion.observe
import androidx.core.content.ContextCompat
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.transition.platform.MaterialSharedAxis
import com.shiftkey.codingchallenge.R
import com.shiftkey.codingchallenge.databinding.FragmentShiftDisplayBinding
import com.shiftkey.codingchallenge.domain.model.ShiftDataModel
import com.shiftkey.codingchallenge.domain.model.ShiftModel
import com.shiftkey.codingchallenge.presentation.adapter.ShiftItemAdapter
import com.shiftkey.codingchallenge.presentation.viewmodel.ShiftViewModel
import com.shiftkey.codingchallenge.utils.Constants
import com.shiftkey.codingchallenge.utils.DateUtil
import com.shiftkey.codingchallenge.utils.HeaderItemDecoration
import com.shiftkey.codingchallenge.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShiftDisplayFragment : Fragment() {

    private var _binding: FragmentShiftDisplayBinding? = null
    private val binding get() = _binding!!
    lateinit var adapter: ShiftItemAdapter
    var shiftList: MutableList<ShiftModel>  =  mutableListOf()
    lateinit var layoutManager: LinearLayoutManager
    val viewModel: ShiftViewModel by viewModels()
    val loadingModel = ShiftModel(Constants.SHIFT_ITEM_TYPE_LOADING)
    private var dateUtil: DateUtil? = null
    var startDate: String = ""
    var endDate: String = ""
    var isLoading = false


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
        _binding = FragmentShiftDisplayBinding.inflate(inflater, container, false)
        val view = _binding!!.root
        initView()
        return view
    }

    private fun initView(){
        dateUtil = DateUtil()
        startDate = dateUtil!!.getCurrentStartDate()
        endDate = dateUtil!!.getEndDate(startDate)
        isLoading = false
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true)
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false)
        initRecyclerview()


        binding.shiftItemRecyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    if(!isLoading){
                        //check if is not currently loading more shifts
                        isLoading = true
                        adapter.addData(loadingModel)
                        viewModel.loadNextWeekShifts(Constants.SEARCH_ADDRESS,Constants.SEARCH_TYPE,startDate)
                    }
                }
            }
        })

        binding.shiftItemRefresh.setOnClickListener {
                refreshList()
        }

        binding.errorOccurredLayout.errorOccurredRetry.setOnClickListener {
            refreshList()
        }

        binding.noNetworkLayout.noNetworkRetry.setOnClickListener {
            refreshList()
        }

        adapter.removeAllData()
        viewModel.loadShifts(Constants.SEARCH_ADDRESS,Constants.SEARCH_TYPE,startDate)

        viewModel.receivedShiftsLiveData.observe(
            viewLifecycleOwner,
            {
                when (it.status) {
                        Status.SUCCESS -> {
                            it.data?.let {
                                    shiftDataModels : ShiftDataModel -> renderList(shiftDataModels)
                            }
                            initializeNextDate() //Make date go the the next date
                            binding.shiftItemRecyclerview.visibility = View.VISIBLE
                            binding.loadingBarLayout.loadingBarLayoutRoot.visibility = View.GONE
                        }
                        Status.LOADING -> {
                            binding.shiftItemRecyclerview.visibility = View.GONE
                            binding.loadingBarLayout.loadingBarLayoutRoot.visibility = View.VISIBLE
                        }
                        Status.ERROR -> {
                            displayErrorLayout()
                        }
                        Status.N0_NETWORK -> {
                            displayNoNetworkLayout()
                     }
                 }
             }
         )

    }

    private fun initRecyclerview(){
        adapter = ShiftItemAdapter(shiftList)
        layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.shiftItemRecyclerview.adapter = adapter
        binding.shiftItemRecyclerview.layoutManager = layoutManager
        binding.shiftItemRecyclerview.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                layoutManager.orientation
            )
        )

        binding.shiftItemRecyclerview.apply {
            addItemDecoration(
                HeaderItemDecoration(this) { itemPosition ->
                    this@ShiftDisplayFragment.adapter.getItemViewType(itemPosition) == Constants.SHIFT_ITEM_TYPE_HEADER
                }
            )
        }
        adapter.notifyDataSetChanged()

    }


    private fun renderList(shiftData: ShiftDataModel) {
         authLoadingBarDisplay()
         val headerModel = ShiftModel(startDate,endDate)
         adapter.addData(headerModel)
         for (availableShiftList in shiftData.shiftData){
            adapter.addListData(availableShiftList.shiftList!!)
         }
    }

    private fun refreshList(){
        adapter.removeAllData()
        startDate = dateUtil!!.getCurrentStartDate()
        endDate = dateUtil!!.getEndDate(startDate)
        viewModel.loadShifts(Constants.SEARCH_ADDRESS,Constants.SEARCH_TYPE,startDate)
    }

    private fun authLoadingBarDisplay(){
      if(adapter.getDataList().size > 0) {
          isLoading = false
          adapter.removeLoader()
      }
    }

    private fun displayNoNetworkLayout(){
        if(adapter.getDataList().size > 0) {

            Toast.makeText(requireContext(),R.string.no_network_text,Toast.LENGTH_SHORT).show()
            if(isLoading)adapter.removeLoader()
            isLoading = false

        } else{
            binding.noNetworkLayout.noNetworkLayoutRoot.visibility = View.VISIBLE
            binding.loadingBarLayout.loadingBarLayoutRoot.visibility = View.GONE
            binding.errorOccurredLayout.errorOccurredLayoutRoot.visibility = View.GONE
            binding.shiftItemRecyclerview.visibility = View.GONE
        }
    }

    private fun displayErrorLayout(){
        if(adapter.getDataList().size > 0) {

            Toast.makeText(requireContext(),R.string.error_occurred_text,Toast.LENGTH_SHORT).show()
            if(isLoading)adapter.removeLoader()
            isLoading = false

        } else{
            binding.noNetworkLayout.noNetworkLayoutRoot.visibility = View.GONE
            binding.loadingBarLayout.loadingBarLayoutRoot.visibility = View.GONE
            binding.errorOccurredLayout.errorOccurredLayoutRoot.visibility = View.VISIBLE
            binding.shiftItemRecyclerview.visibility = View.GONE
        }
    }

    private fun initializeNextDate(){
        startDate = dateUtil!!.getNextStartDate(startDate)
        endDate = dateUtil!!.getEndDate(startDate)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}