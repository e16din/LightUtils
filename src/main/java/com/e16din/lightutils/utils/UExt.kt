package com.e16din.lightutils.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.view.View
import android.widget.TextView
import com.e16din.topactivity.activity
import java.util.regex.Pattern

fun resources(): Resources = Resources.getSystem()

val TextView.string
    get() = this.text.toString()

fun View.openOnClick(url: String) {
    this.setOnClickListener {
        url.open(this.context)
    }
}

fun View.openOnClick(activityCls: Class<out Activity>) {
    this.setOnClickListener {
        activityCls.open(this.context)
    }
}

fun String.open(context: Context = activity()!!) {
    val matcher = Pattern.compile("(https?://|mailto:).+").matcher("")
    val url: String = matcher.reset(this).matches().then(this, "http://$this")

    Intent(Intent.ACTION_VIEW, url.toUri()).start(context)
}

fun Class<out Activity>.open(context: Context = activity()!!) {
    Intent(context, this).start(context)
}

fun startActivity(intent: Intent, context: Context = activity()!!) {
    context.startActivity(
            (context is Activity).then(intent, intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)))
}

fun Intent.start(context: Context = activity()!!) {
    startActivity(this, context)
}

fun String.toUri(): Uri? = Uri.parse(this)

fun <T> Boolean.then(tru: T, fal: T) = if (this) tru else fal
fun <T> Boolean.then(tru: T) = if (this) tru else null