package com.irfan.mandiriTestOption2.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    fun subscribe(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun unsubscribe(){
        disposables.dispose()
    }

    fun clear(){
        disposables.clear()
    }

    fun Disposable.toDisposables() {
        disposables.add(this)
    }

    override fun onCleared() {
        super.onCleared()
        unsubscribe()
    }

}