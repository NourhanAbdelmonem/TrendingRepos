package com.example.aqwastask.framework.utils

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.aqwastask.R
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter(value = ["avatars"], requireAll = true)
fun CircleImageView.avatars(avatars: List<String>?) {
    avatars?.let {
        Glide.with(this)
            .load(it.firstOrNull())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.back_circle_loading)
            .into(this)
    }
}