package com.juarez.marvelheroes.utils

import android.widget.ImageView
import com.bumptech.glide.Glide


fun ImageView.loadImage(url: String) {
    if (url.isNotEmpty()) {
        Glide.with(this)
            .load(url.replace("http", "https"))
            .into(this)
    }
}