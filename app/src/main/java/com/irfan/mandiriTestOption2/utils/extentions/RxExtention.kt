package com.irfan.mandiriTestOption2.utils.extentions

import androidx.lifecycle.MutableLiveData
import com.irfan.mandiriTestOption2.network.ApiClient
import com.irfankurnia.test_sequis.network.ResponseAPI
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

fun <T> Single<T>.applySchedulers(): Single<T> =
    subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.withProgress(progress: MutableLiveData<Boolean>): Single<T> {
    return this.doOnSubscribe { progress.value = true }
        .doOnSuccess { progress.value = false }
        .doOnError { progress.value = false }
}

fun Throwable.get(): ResponseAPI<Any> {
    try {
        if (this is HttpException) {
            val response = response()

            val errorConverter = ApiClient.instance
                .responseBodyConverter<ResponseAPI<Any>>(
                    ResponseAPI::class.java,
                    arrayOfNulls<Annotation>(0)
                )

            response?.errorBody()?.let { errorBody ->
                errorConverter.convert(errorBody)?.let {
                    return it
                }
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return ResponseAPI(
        status = 500,
        success = false)
}