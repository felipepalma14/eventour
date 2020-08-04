package br.com.felipepalm14.eventour.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.felipepalm14.eventour.R
import br.com.felipepalm14.eventour.domain.database.model.Event
import br.com.felipepalm14.eventour.ui.EventListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


@BindingAdapter("loadImageUrl")
fun bindLoadImage(imgView: ImageView, imgUrl: String?) {

    if (imgUrl.isNullOrEmpty()) {
        imgView.setImageResource(R.drawable.ic_baseline_broken_image_24)
        return
    }

    Glide.with(imgView.context)
        .load(imgUrl)
        .apply(
            RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_baseline_broken_image_24)
                .circleCrop()
        )
        .into(imgView)
}