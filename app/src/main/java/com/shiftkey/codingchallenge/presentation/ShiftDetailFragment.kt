package com.shiftkey.codingchallenge.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.shiftkey.codingchallenge.R
import androidx.appcompat.widget.Toolbar


class ShiftDetailFragment : Fragment() {

    lateinit var collapsibleToolbarLayout: CollapsingToolbarLayout
    lateinit var mView: View
    lateinit var actionBar: ActionBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragment_shift_detail, container, false)
        initView()
        return mView
    }

    private fun initView(){

        val toolbar: Toolbar = mView.findViewById(R.id.toolbar) as Toolbar
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
        actionBar = (activity as AppCompatActivity?)!!.supportActionBar!!
        actionBar!!.setDisplayHomeAsUpEnabled(true);

        collapsibleToolbarLayout = mView.findViewById(R.id.collapsing_toolbar_layout)
        collapsibleToolbarLayout.setTitle("Registered Nurse");
        collapsibleToolbarLayout.setExpandedTitleTextAppearance(R.style.AppBarExpanded);
        collapsibleToolbarLayout.setCollapsedTitleTextAppearance(R.style.AppBarCollapsed);
    }

    // function to the button on press
    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                requireActivity().finish()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }

}