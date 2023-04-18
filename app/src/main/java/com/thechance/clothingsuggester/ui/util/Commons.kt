package com.thechance.clothingsuggester.ui

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

fun ImageView.loadImageFromNetwork(context: Context, url: String?, into: ImageView){
    Glide.with(context)
        .load(url)
        .centerCrop()
        .into(into)
}
fun String.degreeFormat(): String = "$this°"
fun String.percentFormat(): String = "$this%"
fun String.speedFormat(): String = "$this m/s"

fun Long.formatTime(): String? {
    val dateFormat = SimpleDateFormat("h:mm a", Locale.US)
    val date = Date(this)
    return dateFormat.format(date)
}

fun Long.formatDate(): String? {
    val dateFormat = SimpleDateFormat("EEEE, d MMM", Locale.US)
    val date = Date(this)
    return dateFormat.format(date)
}

