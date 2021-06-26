package com.irfan.mandiriTestOption2.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.irfan.mandiriTestOption2.R
import com.irfan.mandiriTestOption2.base.BaseActivity
import com.irfan.mandiriTestOption2.databinding.ActivityMainBinding
import com.irfan.mandiriTestOption2.ui.activity.SourceActivity
import com.irfan.mandiriTestOption2.ui.adapter.AdapterClickListener
import com.irfan.mandiriTestOption2.ui.adapter.CategoryAdapter

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getResLayoutId(): Int = R.layout.activity_main

    private val adapter = CategoryAdapter(arrayListOf()).apply {
        listener = object : AdapterClickListener<String> {
            override fun onItemClick(data: String) {
                val intent = Intent(this@MainActivity, SourceActivity::class.java)
                intent.putExtra("catgory", data)
                startActivity(intent)
            }

            override fun onViewClick(view: View, data: String) {

            }

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        binding.rvCategory.adapter = adapter
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvCategory.layoutManager = layoutManager

        val category : MutableList<String> = mutableListOf("business", "entertainment", "general",
        "health", "science", "sports", "technology")

        adapter.addData(category)
    }
}