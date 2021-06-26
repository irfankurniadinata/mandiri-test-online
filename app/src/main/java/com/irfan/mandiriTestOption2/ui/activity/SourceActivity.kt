package com.irfan.mandiriTestOption2.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.irfan.mandiriTestOption2.R
import com.irfan.mandiriTestOption2.base.BaseActivity
import com.irfan.mandiriTestOption2.data.models.Source
import com.irfan.mandiriTestOption2.databinding.ActivitySourceBinding
import com.irfan.mandiriTestOption2.network.State
import com.irfan.mandiriTestOption2.ui.adapter.AdapterClickListener
import com.irfan.mandiriTestOption2.ui.adapter.SourceAdapter

class SourceActivity : BaseActivity<ActivitySourceBinding>() {

    override fun getResLayoutId(): Int = R.layout.activity_source

    private lateinit var viewModel: SourceViewModel

    var category : String? = null

    private val adapter = SourceAdapter(arrayListOf()).apply {
        listener = object : AdapterClickListener<Source> {
            override fun onItemClick(data: Source) {
                val intent = Intent(this@SourceActivity, ArticleActivity::class.java)
                intent.putExtra("source_id", data.id)
                startActivity(intent)
            }

            override fun onViewClick(view: View, data: Source) {

            }

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        initView()
        initViewModel()
    }

    private fun initView() {
        viewModel = ViewModelProviders.of(this).get(SourceViewModel::class.java)
        binding.data = viewModel

        category = intent.getStringExtra("category")

        setupAdapter()
        viewModel.getSourceList(category)
    }

    private fun initViewModel() {
        viewModel.sourceListResult.observe(this, { result ->
            when (result.state) {
                State.LOADING -> {
                    showProgress()
                }
                State.SUCCESSFUL -> {
                    hideProgress()
                    adapter.addData(result.data ?: emptyList())
                }
                State.ERROR -> {
                    hideProgress()
                }
            }
        })
    }

    private fun setupAdapter() {
        binding.rvSource.adapter = adapter
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvSource.layoutManager = layoutManager
    }
}