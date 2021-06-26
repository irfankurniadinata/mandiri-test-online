package com.irfan.mandiriTestOption2.ui.activity

import android.os.Bundle
import com.irfan.mandiriTestOption2.R
import com.irfan.mandiriTestOption2.base.BaseActivity
import com.irfan.mandiriTestOption2.databinding.ActivityDetailArticleBinding

class DetailArticleActivity : BaseActivity<ActivityDetailArticleBinding>() {

    override fun getResLayoutId(): Int = R.layout.activity_detail_article

    var urlArticle : String? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        urlArticle = intent.getStringExtra("url")
        binding.webview.loadUrl(urlArticle ?: "")
    }
}