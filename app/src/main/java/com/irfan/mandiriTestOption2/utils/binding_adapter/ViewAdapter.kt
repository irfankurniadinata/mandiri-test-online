package com.irfan.mandiriTestOption2.utils.binding_adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("app:loadImage")
fun ImageView.setImage(url: String?) {
    if (url == null) return
    Glide.with(this)
        .load(url)
        .into(this)
}