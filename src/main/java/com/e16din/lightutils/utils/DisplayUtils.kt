package com.e16din.lightutils.utils

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Build
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.Display
import android.view.View
import com.e16din.lightutils.model.Size
import java.lang.reflect.InvocationTargetException

val Number.toPx: Float
    get() = (this.toFloat() * resources().displayMetrics.density)

val Number.toDp: Float
    get() = (this.toFloat() / resources().displayMetrics.density)

open class DisplayUtils : ColorUtils() {
    companion object {

        @JvmStatic
        fun dpToPx(dp: Number) = dp.toPx

        @JvmStatic
        fun pxToDp(px: Number) = px.toDp

        @JvmStatic
        fun pxToSp(px: Float): Float {
            val scaledDensity = resources().displayMetrics.scaledDensity
            return px / scaledDensity
        }

        @JvmStatic
        fun pxToMm(px: Float): Float {
            val dm = resources().displayMetrics
            return px / TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_MM, 1f, dm)
        }

        @JvmStatic
        fun spToPx(sp: Int): Int {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp.toFloat(), resources().displayMetrics).toInt()
        }

        @JvmStatic
        fun setElevation(view: View, levelPx: Int) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                view.elevation = levelPx.toFloat()
            }
        }

        @JvmStatic
        fun getScreenSize(activity: Activity): Size {
            return getScreenSize(activity.windowManager.defaultDisplay)
        }

        @JvmStatic
        fun getScreenSize(display: Display): Size {
            var realWidth: Int
            var realHeight: Int

            when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 -> {
                    //new pleasant way to get real metrics
                    val realMetrics = DisplayMetrics()
                    display.getRealMetrics(realMetrics)
                    realWidth = realMetrics.widthPixels
                    realHeight = realMetrics.heightPixels

                }
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH -> //reflection for this weird in-between time
                    try {
                        val mGetRawH = Display::class.java.getMethod("getRawHeight")
                        val mGetRawW = Display::class.java.getMethod("getRawWidth")
                        realWidth = mGetRawW.invoke(display) as Int
                        realHeight = mGetRawH.invoke(display) as Int
                    } catch (e: NoSuchMethodException) {
                        //this may not be 100% accurate, but it's all we've got
                        realWidth = display.width
                        realHeight = display.height
                    } catch (e: SecurityException) {
                        realWidth = display.width
                        realHeight = display.height
                    } catch (e: IllegalAccessException) {
                        realWidth = display.width
                        realHeight = display.height
                    } catch (e: IllegalArgumentException) {
                        realWidth = display.width
                        realHeight = display.height
                    } catch (e: InvocationTargetException) {
                        realWidth = display.width
                        realHeight = display.height
                    }
                else -> {
                    //This should be close, as lower API devices should not have window navigation bars
                    realWidth = display.width
                    realHeight = display.height
                }
            }

            return Size(realWidth, realHeight)
        }

        @JvmStatic
        fun takeScreenshot(view: View): Bitmap {
            val bitmap = Bitmap.createBitmap(view.width, view.height,
                    Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)

            val backgroundDrawable = view.background
            if (backgroundDrawable != null) {
                backgroundDrawable.draw(canvas)
            } else {
                canvas.drawColor(Color.WHITE)
            }
            view.draw(canvas)

            return bitmap
        }
    }
}
