package com.e16din.lightutils.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.os.Build
import android.view.View
import android.widget.TextView
import com.e16din.topactivity.app
import com.e16din.topactivity.topActivity
import java.util.regex.Pattern

val resources
    get() = app?.resources

val TextView.string
    get() = this.text.toString()


fun View.startOnClick(url: String) {
    this.setOnClickListener {
        url.start(this.context)
    }
}

fun View.startOnClick(activityCls: Class<out Activity>) {
    this.setOnClickListener {
        activityCls.start(this.context)
    }
}

fun String.start(context: Context = topActivity!!) {
    val matcher = Pattern.compile("(https?://|mailto:).+").matcher("")
    val url: String = matcher.reset(this).matches().then(this, "http://$this")

    Intent(Intent.ACTION_VIEW, url.toUri()).start(context)
}

fun startActivity(intent: Intent, context: Context = topActivity!!) {
    context.startActivity(
            (context is Activity).then(intent, intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)))
}

fun Intent.start(context: Context = topActivity!!) {
    startActivity(this, context)
}

fun Class<out Activity>.start(context: Context = topActivity!!) {
    Intent(context, this).start(context)
}

fun String.toUri(): Uri? = Uri.parse(this)

fun Int.getColor(theme: Resources.Theme = topActivity!!.theme) =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            resources?.getColor(this, theme)
        } else {
            resources?.getColor(this)
        }

fun Int.getString() = resources?.getString(this)!!
fun Int.getDimension() = resources?.getDimension(this)

fun <T> Boolean.then(tru: T, fal: T) = if (this) tru else fal
fun <T> Boolean.then(tru: T) = if (this) tru else null