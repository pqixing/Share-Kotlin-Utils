package com.pqixing.utils.utils

import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.support.annotation.ColorInt
import android.support.annotation.StringRes
import android.util.Log
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import com.pqixing.utils.context
import com.pqixing.utils.toPx
import java.lang.ref.WeakReference


/**
 * Created by pqixi on 2016/10/25 0025.
 * 显示类型的工具类
 */

object ToastUtil {
    private var lToast: WeakReference<Toast>? = null//上次toas缓存对象

    @ColorInt
    private val DEF_TEXT_COLOR = Color.parseColor("#FFFFFF")
    @ColorInt
    private val ERROR_COLOR = Color.parseColor("#D50000")
    @ColorInt
    private val INFO_COLOR = Color.parseColor("#3F51B5")
    @ColorInt
    private val SUCCESS_COLOR = Color.parseColor("#388E3C")
    @ColorInt
    private val WARNING_COLOR = Color.parseColor("#FFA900")
    @ColorInt
    val DEF_TINT_COLOR = Color.parseColor("#77000000")

    fun d(resId: Int, duration: Int) {
        show(resId, null, null, duration, DEF_TEXT_COLOR, DEF_TINT_COLOR)
    }

    fun d(str: String, duration: Int) {
        show(str, null, null, duration, DEF_TEXT_COLOR, DEF_TINT_COLOR)
    }

    fun i(resId: Int, duration: Int) {
        show(resId, null, null, duration, DEF_TEXT_COLOR, INFO_COLOR)
    }

    fun i(str: String, duration: Int) {
        show(str, null, null, duration, DEF_TEXT_COLOR, INFO_COLOR)
    }

    fun w(resId: Int, duration: Int) {
        show(resId, null, null, duration, DEF_TEXT_COLOR, WARNING_COLOR)
    }

    fun w(str: String, duration: Int) {
        show(str, null, null, duration, DEF_TEXT_COLOR, WARNING_COLOR)
    }

    fun e(str: String, duration: Int) {
        show(str, null, null, duration, DEF_TEXT_COLOR, ERROR_COLOR)
    }

    fun e(resId: Int, duration: Int) {
        show(resId, null, null, duration, DEF_TEXT_COLOR, ERROR_COLOR)
    }

    fun s(str: String, duration: Int) {
        show(str, null, null, duration, DEF_TEXT_COLOR, SUCCESS_COLOR)
    }


    fun show(@StringRes redId: Int, logo: Drawable?, animation: Boolean?, duration: Int, textColor: Int, tintColor: Int) {
        show(context().getString(redId), logo, animation, duration, textColor, tintColor)
    }

    fun show(str: String, logo: Drawable?, animation: Boolean?, duration: Int, textColor: Int, tintColor: Int) {
        var logo = logo
        var animation = animation
        var duration = duration
        var textColor = textColor
        var tintColor = tintColor
        if (logo == null)
            logo = context().applicationInfo.loadIcon(context().packageManager)
        logo?.setBounds(0, 0, 20.toPx(), 20.toPx())
        if (duration != Toast.LENGTH_LONG) duration = Toast.LENGTH_LONG
        if (textColor == 0) textColor = DEF_TEXT_COLOR
        if (tintColor == 0) tintColor = DEF_TINT_COLOR
        if (animation == null) animation = true

        val t = createToast(str, logo!!, animation, duration, textColor, tintColor);
        Pool.excUi(Runnable { realShow(t) })
    }

    fun createToast(str: String, logo: Drawable, animation: Boolean, duration: Int, textColor: Int, tintColor: Int): Toast {
        val t = Toast(context())
        t.duration = duration
        val tView = TextView(context())
        tView.text = str
        tView.textSize = 18f
        tView.setPadding(17f.toPx(), 7f.toInt(), 17f.toPx(), 7f.toPx())
        tView.setTextColor(textColor)
        tView.gravity = Gravity.CENTER
        tView.compoundDrawablePadding = 7f.toInt()
        tView.setCompoundDrawables(logo, null, null, null)
        val radius = 19f.toPx()
        val bgDrawable = ShapeDrawable(RoundRectShape(floatArrayOf(radius.toFloat(), radius.toFloat(), radius.toFloat(), radius.toFloat(), radius.toFloat(), radius.toFloat(), radius.toFloat(), radius.toFloat()), null, null))
        bgDrawable.paint.isAntiAlias = true
        bgDrawable.paint.style = Paint.Style.FILL_AND_STROKE
        bgDrawable.paint.color = tintColor
        tView.background = bgDrawable
        t.view = tView
        return t
    }

    private fun realShow(t: Toast) {
        lToast?.get()?.cancel()
        lToast = WeakReference(t)
        t.show()
    }
    fun show(message:String, duration: Int = Toast.LENGTH_SHORT,level: Int = Log.DEBUG) = when(level){
        Log.VERBOSE-> d(message,duration)
        Log.DEBUG-> d(message,duration)
        Log.INFO-> i(message,duration)
        Log.WARN-> w(message,duration)
        Log.ERROR-> e(message,duration)
        else ->{}
    }
}
