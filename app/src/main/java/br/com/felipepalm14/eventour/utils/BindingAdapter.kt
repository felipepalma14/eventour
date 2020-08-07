package br.com.felipepalm14.eventour.utils

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import br.com.felipepalm14.eventour.R
import br.com.felipepalm14.eventour.domain.database.model.Cupom
import br.com.felipepalm14.eventour.domain.database.model.Event
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*


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
//                .circleCrop()
        )
        .into(imgView)
}

@BindingAdapter("currency")
fun bindCurrency(textView: TextView, event: Event) {

    val formatter = NumberFormat.getCurrencyInstance()
    if (formatter is DecimalFormat) {
        formatter.isDecimalSeparatorAlwaysShown = true
    }

    if (event.price > 0 && event.cupons.isNotEmpty()) {
        val formatedCurrentCurrency = formatter.format(event.price)
        val cupom = event.cupons[0]
        val discount = (cupom.discount.toInt()  * event.price) / 100
        val formatedDiscountCurrency = formatter.format(event.price - discount)
        textView.text = "De $formatedCurrentCurrency por $formatedDiscountCurrency"
    }else if(event.price > 0  && event.cupons.isEmpty()) {
        val formatedCurrentCurrency = formatter.format(event.price)
        textView.text = "Por $formatedCurrentCurrency"
    }else{
        textView.text = textView.context.getString(R.string.text_unknow_price)
    }
}

@SuppressLint("SimpleDateFormat")
@BindingAdapter("postDate")
fun bindPostDate(textView: TextView, postDate: Long){
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    if(postDate != null || postDate > 0) {
        val dateConverted = Date(postDate)
        try {
            val dateFormatted = formatter.format(dateConverted)
            textView.text = "Data de postagem: $dateFormatted"
        } catch (e: Exception) {
            textView.text = textView.context.getString(R.string.text_unknow_postdate)
        }
    }
}

@BindingAdapter("discount")
fun bindDiscount(textView: TextView, cupons:List<Cupom>){
    if(cupons.isNullOrEmpty()) {
        textView.visibility = View.GONE
    }else{
        val cupom = cupons[0]
        textView.text = "${cupom.discount.toInt()}% Off"
    }
}