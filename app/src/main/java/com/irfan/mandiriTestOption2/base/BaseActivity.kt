package com.irfan.mandiriTestOption2.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.irfan.mandiriTestOption2.ui.custom.DialogProgress

abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {
    /**
     * This variable is used for binding the view
     */
    protected lateinit var binding: B

    /**
     * This function is used for set the view layout
     */
    @LayoutRes
    protected abstract fun getResLayoutId(): Int

    private lateinit var progressDialog : DialogProgress

    /**
     * This function is used for set the action when the activity was created
     */
    protected abstract fun onActivityCreated(savedInstanceState: Bundle?)

    /**
     * This function is used for init all function when activity is created
     * There're have function for binding the view with data binding
     * There're also have function for listen the connection state change
     * and have function to observe the error state
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<B>(this, getResLayoutId())
            .apply {
                lifecycleOwner = this@BaseActivity
            }

        progressDialog = DialogProgress(this)
        onActivityCreated(savedInstanceState)
    }

    fun showProgress() : DialogProgress {
        try {
            progressDialog.show()
        }catch (e : Exception) {
            e.printStackTrace()
        }
        return progressDialog
    }

    fun hideProgress() {
        try {
            progressDialog.dismiss()
        }catch (e : Exception) {
            e.printStackTrace()
        }
    }
}