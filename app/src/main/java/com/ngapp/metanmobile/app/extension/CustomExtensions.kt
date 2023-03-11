package com.ngapp.metanmobile.app.extension

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.ParcelFileDescriptor
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun haveN(): Boolean {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
}

fun haveQ(): Boolean {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
}

fun haveO(): Boolean {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
}

fun haveR(): Boolean {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.R
}

fun haveS(): Boolean {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
}

fun formatStringToDate(dateString: String?): Date? {
    val sourceSdf = SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.ENGLISH)
    return if (dateString != null) {
        return sourceSdf.parse(dateString)
    } else {
        null
    }
}

fun formatDateStringToUnix(dateString: String?): Long {
    val l = LocalDate.parse(
        dateString,
        DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm:ss Z", Locale.ENGLISH)
    )
    return l.atStartOfDay(ZoneId.systemDefault()).toInstant().epochSecond
}

fun formatUnixDataToString(unixDate: Long): String {
    val sdf = SimpleDateFormat("dd MMMM yyyy")
    val dated = Date(unixDate * 1000)
    return sdf.format(dated)
}

fun shortFormatUnixDataToString(unixDate: Long): String {
    val sdf = SimpleDateFormat("dd.MM.yyyy")
    val dated = Date(unixDate * 1000)
    return sdf.format(dated)
}

fun fromStringToListFloat(stringListString: String): List<Float?> {
    return if (stringListString.isNotEmpty()) {
        stringListString.split(",").map { it.toFloatOrNull() }
    } else {
        emptyList()
    }
}

fun bitmapDescriptor(
    context: Context,
    vectorResId: Int
): BitmapDescriptor? {

    // retrieve the actual drawable
    val drawable = ContextCompat.getDrawable(context, vectorResId) ?: return null
    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
    val bm = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )

    // draw it onto the bitmap
    val canvas = android.graphics.Canvas(bm)
    drawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bm)
}

fun saveImage(context: Context, bitmap: Bitmap, name: String): String {
    context.openFileOutput(name, Context.MODE_PRIVATE).use { fos ->
        bitmap.compress(Bitmap.CompressFormat.JPEG, 25, fos)
    }
    return context.filesDir.absolutePath
}

