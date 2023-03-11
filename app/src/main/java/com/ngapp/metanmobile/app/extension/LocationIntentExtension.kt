package com.ngapp.metanmobile.app.extension

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.net.Uri
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.data.model.dto.station.StationDto

fun Context.createLocationIntent(station: StationDto) {
    val uriYandex = if (!station.yandexTag.isNullOrEmpty()) {
        "https://yandex.ru/maps/org/${station.yandexTag}"
    } else {
        "yandexmaps://maps.yandex.ru/?pt=0,0&z=12&text=${station.latitude},${station.longitude}&l=map"
    }
    val intentYandex = Intent(Intent.ACTION_VIEW, Uri.parse(uriYandex))
    intentYandex.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    intentYandex.setPackage("ru.yandex.yandexmaps")


    val uriGoogle = if (!station.googleMapsTag.isNullOrEmpty()) {
        "https://maps.google.com/maps?cid=${station.googleMapsTag}"
    } else {
        "geo:0,0?q=${station.latitude},${station.longitude}"
    }
    val intentGoogle = Intent(Intent.ACTION_VIEW, Uri.parse(uriGoogle))
    intentGoogle.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    intentGoogle.setPackage("com.google.android.apps.maps")

    val title = getString(R.string.text_select_application)
    val chooserIntent = Intent.createChooser(intentGoogle, title)
    chooserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    val arr = arrayOfNulls<Intent>(1)
    arr[0] = intentYandex
    chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arr)
    val activity = (this as ContextWrapper).baseContext
    activity.startActivity(chooserIntent)
}