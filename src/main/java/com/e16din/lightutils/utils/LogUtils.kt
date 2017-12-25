package com.e16din.lightutils.utils

import android.util.Log
import com.e16din.lightutils.LightUtils
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

enum class LogType { D, I, W, E }

const val TAG_DEBUG = "debug"

fun String?.logD(tag: String = "${TAG_DEBUG}D") {
    this.log(tag, LogType.D)
}

fun String?.logI(tag: String = "${TAG_DEBUG}I") {
    this.log(tag, LogType.I)
}

fun String?.logW(tag: String = "${TAG_DEBUG}W") {
    this.log(tag, LogType.W)
}

fun String?.logE(tag: String = "${TAG_DEBUG}E") {
    this.log(tag, LogType.E)
}

fun String?.log(tag: String = TAG_DEBUG, type: LogType = LogType.D) {
    LogUtils.log(this, tag, type)
}


open class LogUtils : TextViewUtils() {
    companion object {

        private const val LOG_PART_SIZE = 4000

        private const val DEFAULT_INDENT_SPACES = 4

        @JvmStatic
        fun log(obj: Any?, tag: String = TAG_DEBUG, type: LogType = LogType.D) {
            val isNull = obj != null
            logJson(isNull.then("$obj", "log object is null!"),
                    tag,
                    type = isNull.then(type, LogType.E))
        }

        @JvmStatic
        fun logJson(message: String?,
                    tag: String = TAG_DEBUG,
                    indentSpaces: Int = DEFAULT_INDENT_SPACES,
                    type: LogType = LogType.D) {

            if (!LightUtils.isDebug) return // else {

            if (!message.isNullOrEmpty() && message!!.startsWith("{")) {
                val jsonObject: JSONObject
                try {
                    jsonObject = JSONObject(message)
                    val logStr = jsonObject.toString(indentSpaces)

                    logLong(logStr, tag, type)
                } catch (e: JSONException) {
                    e.printStackTrace()

                    logLong("$message", tag, LogType.E)
                }

            } else if (!message.isNullOrEmpty() && message!!.startsWith("[")) {
                val jsonArray: JSONArray
                try {
                    jsonArray = JSONArray(message)

                    log("[", tag, type)
                    (0 until jsonArray.length())
                            .map { jsonArray.get(it) as JSONObject }
                            .forEach { logLong(it.toString(indentSpaces), tag, type) }
                    log("]", tag, type)

                } catch (e: JSONException) {
                    e.printStackTrace()

                    logLong("$message", tag, LogType.E)
                }

            } else {
                logLong(message, tag, type)
            }
        }

        @JvmStatic
        fun logLong(message: String?, tag: String = TAG_DEBUG, type: LogType = LogType.D) {
            if (!LightUtils.isDebug) return

            if (message != null && message.length > LOG_PART_SIZE) {
                val nextPart = message.substring(0, LOG_PART_SIZE)
                log(nextPart, tag, type)

                logLong(tag, message.substring(LOG_PART_SIZE), type)

            } else {
                log(message, tag, type)
            }
        }

        @JvmStatic
        private fun log(message: String?, tag: String, type: LogType) {
            when (type) {
                LogType.D -> Log.d(tag, message)
                LogType.I -> Log.i(tag, message)
                LogType.W -> Log.w(tag, message)
                LogType.E -> Log.e(tag, message)
            }
        }
    }
}
