package com.pqixing.utils

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.pqixing.utils.utils.LogUtil
import com.pqixing.utils.utils.Share
import com.pqixing.utils.utils.ToastUtil

/**
 * Created by pqixing on 17-8-3.
 */
//standard ---start
fun context(): Context = Q.app.baseContext

fun application(): Application = Q.app
//standard ---end

//String ---start
fun <T> String.getSp(t: T): T = Share(this, t).findPreference(this@getSp, t)

fun <T> String.setSp(t: T): T = t.apply { Share(this@setSp, t).putPreference(this@setSp, t) }
fun String.log(level: Int = Log.DEBUG): String = apply { LogUtil.log(this) }
fun String.show(level: Int = Log.DEBUG, duration: Int = Toast.LENGTH_SHORT): String = apply { ToastUtil.show(this, duration) }
//String ---end

//Context ---start
fun View.updateParams(update: (p: ViewGroup.LayoutParams) -> Unit) = layoutParams.apply {
    update(this)
    this@updateParams.layoutParams = this
}

fun View.size(w: Int = width, h: Int = height) = updateParams { p -> p.width = w; p.height = h }
//Context ---end

//Display ---start
fun Int.toPx(): Int = (context().resources.displayMetrics.density * this).toInt()

fun Float.toPx(): Int = (context().resources.displayMetrics.density * this).toInt()
//Display ---end



