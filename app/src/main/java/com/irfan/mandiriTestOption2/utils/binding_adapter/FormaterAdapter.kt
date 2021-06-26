package com.irfan.mandiriTestOption2.utils.binding_adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.irfan.mandiriTestOption2.utils.DateFormatter

@BindingAdapter("app:setTextDateFormat")
fun setTextDateFormat(view: TextView, value: String?) {
    if (value == null) return
    view.text = DateFormatter.formatSimple(value)
}