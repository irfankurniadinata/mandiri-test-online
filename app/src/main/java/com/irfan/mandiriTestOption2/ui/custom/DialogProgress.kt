package com.irfan.mandiriTestOption2.ui.custom

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import com.irfan.mandiriTestOption2.R

class DialogProgress(context : Context) : Dialog(context){

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_progress)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

}