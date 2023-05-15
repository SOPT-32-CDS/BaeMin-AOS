package sopt.haeti.baeminaos.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageurl: String) {
    view.load(imageurl)
}