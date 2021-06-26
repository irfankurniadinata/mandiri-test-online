package com.irfan.mandiriTestOption2.ui.activity

import androidx.lifecycle.MutableLiveData
import com.irfan.mandiriTestOption2.base.BaseViewModel
import com.irfan.mandiriTestOption2.data.api.NewsAPI
import com.irfan.mandiriTestOption2.data.models.Source
import com.irfan.mandiriTestOption2.network.ApiClient
import com.irfan.mandiriTestOption2.network.Data
import com.irfan.mandiriTestOption2.network.State
import com.irfan.mandiriTestOption2.utils.Constant
import com.irfan.mandiriTestOption2.utils.extentions.applySchedulers
import com.irfan.mandiriTestOption2.utils.extentions.get

class SourceViewModel : BaseViewModel() {
    private val newsAPI = ApiClient.instance.create(NewsAPI::class.java)

    var sourceListResult = MutableLiveData<Data<List<Source>>>()

    fun getSourceList(category: String?) {
        newsAPI.getSource(category = category ,apiKey = Constant.KEY_API)
            .applySchedulers()
            .doOnSubscribe { sourceListResult.value = Data(state = State.LOADING) }
            .subscribe({ response ->
                sourceListResult.value = Data(state = State.SUCCESSFUL, data = response.sources)
            }, { throwable ->
                sourceListResult.value = Data(state = State.ERROR, error = throwable.get().error)
            }).toDisposables()
    }
}