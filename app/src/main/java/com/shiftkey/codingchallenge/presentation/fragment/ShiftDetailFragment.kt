package com.shiftkey.codingchallenge.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.transition.platform.MaterialSharedAxis
import com.shiftkey.codingchallenge.R
import com.shiftkey.codingchallenge.databinding.FragmentShiftDetailBinding
import com.shiftkey.codingchallenge.databinding.FragmentShiftDisplayBinding
import com.shiftkey.codingchallenge.domain.model.ShiftModel
import com.shiftkey.codingchallenge.utils.DateUtil





class ShiftDetailFragment : Fragment() {

    private var _binding: FragmentShiftDetailBinding? = null
    private val binding get() = _binding!!
    lateinit var actionBar: ActionBar
    private var shiftModel: ShiftModel? = null
    val dateUtil: DateUtil = DateUtil()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentShiftDetailBinding.inflate(inflater, container, false)
        val view = _binding!!.root
        initView()
        return view
    }

    private fun initView(){

        shiftModel = arguments?.getSerializable("shiftDetail") as ShiftModel
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true)
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false)
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)
        actionBar = (activity as AppCompatActivity?)!!.supportActionBar!!
        actionBar!!.setDisplayHomeAsUpEnabled(true);
        binding.collapsingToolbarLayout.title = shiftModel!!.localizedSpecialty!!.name;
        binding.collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.AppBarExpanded)
        binding.collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.AppBarCollapsed)

        binding.detailsLayout.shiftDetailTimezone.text = shiftModel!!.timezone.toString()
        binding.detailsLayout.shiftDetailIsPremiumRate.text = if (shiftModel!!.isPremiumRate == true) "Is Premium Rate" else "Not Premium Rate"
        binding.detailsLayout.shiftDetailIsCovid.text = if (shiftModel!!.isCovid == true) "Is Covid" else "Not Covid"
        binding.detailsLayout.shiftDetailShiftKind.text = shiftModel!!.shiftKind
        binding.detailsLayout.shiftDetailFacilityType.text = shiftModel!!.facilityType!!.name
        binding.detailsLayout.shiftDetailSkillName.text = shiftModel!!.skill!!.name
        binding.detailsLayout.shiftDetailSpecialtyName.text = shiftModel!!.localizedSpecialty!!.name
        binding.detailsLayout.shiftDetailsStartDate.text = dateUtil.formatDateTimeIn12HourFormat(shiftModel!!.normalizedStartDateTime.toString())
    }

    // function to the button on press
    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val navController = NavHostFragment.findNavController(this@ShiftDetailFragment)
                navController.popBackStack()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }

}