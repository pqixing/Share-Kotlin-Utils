package com.pqixing.utils

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.pqixing.utils.utils.Share

/**
 * Created by pqixing on 17-8-3.
 */

object StandardUtils {
    lateinit var app: Application
    fun init(application: Application) {
        app = application
    }
}
//standard ---start
fun context(): Context = StandardUtils.app.baseContext
fun application(): Application = StandardUtils.app
//standard ---end

//String ---start
fun <T> String.getSp(t: T): T = Share(this, t).findPreference(this@getSp, t)
fun <T> String.setSp(t: T): T = t.apply { Share(this@setSp, t).putPreference(this@setSp, t) }
fun String.log(level: Int = Log.DEBUG): String = apply { Log.d("-----------", this) }
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
fun Float.toPx(): Float = context().resources.displayMetrics.density
//Display ---end



