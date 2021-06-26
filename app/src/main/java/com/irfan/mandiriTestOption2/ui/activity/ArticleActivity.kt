package com.irfan.mandiriTestOption2.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.irfan.mandiriTestOption2.R
import com.irfan.mandiriTestOption2.base.BaseActivity
import com.irfan.mandiriTestOption2.data.models.Article
import com.irfan.mandiriTestOption2.databinding.ActivityArticleBinding
import com.irfan.mandiriTestOption2.network.State
import com.irfan.mandiriTestOption2.ui.adapter.AdapterClickListener
import com.irfan.mandiriTestOption2.ui.adapter.ArticleAdapter
import com.irfan.mandiriTestOption2.utils.PaginationScrollListener

class ArticleActivity : BaseActivity<ActivityArticleBinding>() {

    override fun getResLayoutId(): Int = R.layout.activity_article

    private lateinit var viewModel: ArticleViewModel

    private val adapter = ArticleAdapter(arrayListOf()).apply {
        listener = object : AdapterClickListener<Article> {
            override fun onItemClick(data: Article) {
                val intent = Intent(this@ArticleActivity, DetailArticleActivity::class.java)
                intent.putExtra("url", data.url)
                startActivity(intent)
            }

            override fun onViewClick(view: View, data: Article) {

            }

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        initView()
        initViewModel()
    }

    private fun initView() {
        viewModel = ViewModelProviders.of(this).get(ArticleViewModel::class.java)
        binding.data = viewModel

        viewModel.sources = intent.getStringExtra("source_id")

        viewModel.getNewsList()
        setupAdapter()
        binding.btnSearch.setOnClickListener {
            adapter.removeData()
            viewModel.searchArticle(binding.etSearch.text.toString())
        }
    }

    private fun setupAdapter() {
        binding.rvArticle.adapter = adapter
        val layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        binding.rvArticle.layoutManager = layoutManager
        binding.rvArticle.addOnScrollListener(object : PaginationScrollListener(layoutManager){
            override fun canLoadMore(): Boolean {
                return !viewModel.allDataLoaded
                        && viewModel.articleListResult.value?.state != State.LOADING
                        && viewModel.articleListResult.value?.state != State.ERROR
            }

            override fun loadMoreItems() {
                viewModel.fetchNextPage()
            }

        })
    }

    private fun initViewModel() {
        viewModel.articleListResult.observe(this, { result ->
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
}