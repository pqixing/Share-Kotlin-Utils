/*
 * Copyright (c) 2013. wyouflf (wyouflf@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pqixing.utils.utils

import android.text.TextUtils
import android.util.Log

import com.pqixing.utils.Q


/**
 * Log工具，类似android.util.Log。
 * tag自动产生，格式: customTagPrefix:className.methodName(L:lineNumber),
 * customTagPrefix为空时只输出：className.methodName(L:lineNumber)。
 * Author: wyouflf
 * Date: 13-7-24
 * Time: 下午12:23
 */
object LogUtil {

    var customTagPrefix = "qxp_log"

    private fun generateTag(): String {
        val caller = Throwable().stackTrace[2]
        var tag = "%s.%s(L:%d)"
        var callerClazzName = caller.className
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1)
        tag = String.format(tag, callerClazzName, caller.methodName, caller.lineNumber)
        tag = if (TextUtils.isEmpty(customTagPrefix)) tag else customTagPrefix + ":" + tag
        return tag
    }

    fun d(content: String) {
        if (!Q.isDebug()) return
        val tag = generateTag()

        Log.d(tag, content)
    }

    fun d(content: String, tr: Throwable) {
        if (!Q.isDebug()) return
        val tag = generateTag()

        Log.d(tag, content, tr)
    }

    fun e(content: String) {
        if (!Q.isDebug()) return
        val tag = generateTag()

        Log.e(tag, content)
    }

    fun e(content: String, tr: Throwable) {
        if (!Q.isDebug()) return
        val tag = generateTag()

        Log.e(tag, content, tr)
    }

    fun i(content: String) {
        if (!Q.isDebug()) return
        val tag = generateTag()

        Log.i(tag, content)
    }

    fun i(content: String, tr: Throwable) {
        if (!Q.isDebug()) return
        val tag = generateTag()

        Log.i(tag, content, tr)
    }

    fun v(content: String) {
        if (!Q.isDebug()) return
        val tag = generateTag()

        Log.v(tag, content)
    }

    fun v(content: String, tr: Throwable) {
        if (!Q.isDebug()) return
        val tag = generateTag()

        Log.v(tag, content, tr)
    }

    fun w(content: String) {
        if (!Q.isDebug()) return
        val tag = generateTag()

        Log.w(tag, content)
    }

    fun w(content: String, tr: Throwable) {
        if (!Q.isDebug()) return
        val tag = generateTag()

        Log.w(tag, content, tr)
    }

    fun w(tr: Throwable) {
        if (!Q.isDebug()) return
        val tag = generateTag()

        Log.w(tag, tr)
    }


    fun wtf(content: String) {
        if (!Q.isDebug()) return
        val tag = generateTag()

        Log.wtf(tag, content)
    }

    fun wtf(content: String, tr: Throwable) {
        if (!Q.isDebug()) return
        val tag = generateTag()

        Log.wtf(tag, content, tr)
    }

    fun wtf(tr: Throwable) {
        if (!Q.isDebug()) return
        val tag = generateTag()

        Log.wtf(tag, tr)
    }

    fun log(message:String,level: Int = Log.DEBUG) = when(level){
        Log.VERBOSE-> v(message)
        Log.DEBUG-> d(message)
        Log.INFO-> i(message)
        Log.WARN-> w(message)
        Log.ERROR-> e(message)
        else ->{}
    }
}
