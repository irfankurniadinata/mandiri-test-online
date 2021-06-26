package com.irfan.mandiriTestOption2.ui.activity

import androidx.lifecycle.MutableLiveData
import com.irfan.mandiriTestOption2.base.BaseViewModel
import com.irfan.mandiriTestOption2.data.api.NewsAPI
import com.irfan.mandiriTestOption2.data.models.Article
import com.irfan.mandiriTestOption2.network.ApiClient
import com.irfan.mandiriTestOption2.network.Data
import com.irfan.mandiriTestOption2.network.State
import com.irfan.mandiriTestOption2.utils.Constant
import com.irfan.mandiriTestOption2.utils.extentions.applySchedulers
import com.irfan.mandiriTestOption2.utils.extentions.get

class ArticleViewModel : BaseViewModel() {
    private val newsAPI = ApiClient.instance.create(NewsAPI::class.java)

    var articleListResult = MutableLiveData<Data<List<Article>>>()
    var allDataLoaded: Boolean = false
    private var page: Int = 1
    private val pageSize: Int = 8
    var sources: String? = null
    private var keyword: String? = null

    fun getNewsList() {
        if (articleListResult.value?.state == State.LOADING) { return }
        newsAPI.getNews(sources = sources, q = keyword, from = "2021-05-26", sortBy = "publishedAt",
            apiKey = Constant.KEY_API, page = page, pageSize = pageSize)
            .applySchedulers()
            .doOnSubscribe { articleListResult.value = Data(state = State.LOADING) }
            .subscribe({ response ->
                allDataLoaded = response.articles?.size ?: 0 < pageSize
                articleListResult.value = Data(state = State.SUCCESSFUL, data = response.articles)
            }, { throwable ->
                articleListResult.value = Data(state = State.ERROR, error = throwable.get().error)
            }).toDisposables()
    }

    fun fetchNextPage() {
        page += 1
        getNewsList()
    }

    fun searchArticle(keyword: String?) {
        this.keyword = keyword
        page = 1
        getNewsList()
    }
}