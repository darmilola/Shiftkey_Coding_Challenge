package com.shiftkey.codingchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen


import androidx.core.content.ContextCompat

import android.os.Build
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ShiftItemAdapter
    lateinit var layoutManager: LinearLayoutManager
    val arrayList = ArrayList<String>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_main)
        initView()
    }


    private fun initView(){
        recyclerView = findViewById(R.id.shift_item_recyclerview)
        arrayList.add("")
        arrayList.add("")
        arrayList.add("")
        arrayList.add("")
        arrayList.add("")
        arrayList.add("")
        arrayList.add("")
        arrayList.add("")
        arrayList.add("")
        arrayList.add("")
        arrayList.add("")
        arrayList.add("")
        arrayList.add("")
        adapter = ShiftItemAdapter(arrayList)
        layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
    }


    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = ContextCompat.getColor(this,R.color.grey_white)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }
}