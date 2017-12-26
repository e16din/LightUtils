package com.e16din.topactivity

import android.app.Activity
import java.util.*

class RxTopActivity {
    companion object {
        fun created():Observable  {
            return Observable()
        }

        fun started():Observable  {
            return Observable()
        }

        fun resumed():Observable  {
            return Observable()
        }

        fun paused():Observable  {
            return Observable()
        }

        fun stopped():Observable {//todo: create own Observable
            return Observable()
        }

        fun destroyed():Observable  {
            return Observable()
        }
    }
}

fun Observable.disposeOnStop(activity: Activity): Observable {
    this.deleteObservers()
    return this
}

fun Observable.disposeOnPause(activity: Activity): Observable {
    this.deleteObservers()
    return this
}

