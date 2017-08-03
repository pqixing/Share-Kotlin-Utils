package com.pqixing.utils.utils

import android.os.Handler
import android.os.Looper
import com.pqixing.utils.log

import java.util.concurrent.ExecutorService
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 * Created by pqixing on 17-1-23.
 */

object Pool {
    private var pool: ExecutorService? = null
    private var mainHandler: Handler? = null

    fun exc(cmd: Runnable) {
        init()
        pool?.execute(Task(cmd))
    }

    fun excDelay(cmd: Runnable, delay: Long) {
        init()
        mainHandler!!.postDelayed(Task(cmd, delay > 0), delay)
    }

    fun excUi(cmd: Runnable) {
        excUiDelay(0, cmd)
    }

    fun excUiDelay(delay: Long, cmd: Runnable) {

        if (delay <= 0 && "main" == Thread.currentThread().name)
            Task(cmd).run()
        else
            mainHandler?.postDelayed(Task(cmd), delay)
    }

    fun init() {
        if (pool == null)
            pool = ThreadPoolExecutor(4, 10, 5, TimeUnit.MINUTES, LinkedBlockingQueue<Runnable>(100))
        if (mainHandler == null)
            mainHandler = Handler(Looper.getMainLooper())
    }


    private class Task @JvmOverloads constructor(private val cmd: Runnable?, private val runOnThread: Boolean = false) : Runnable {

        override fun run() {
            if (cmd == null) return

            if (runOnThread) {
                exc(Task(cmd))
                return
            }
            val startTime = System.currentTimeMillis()
            "run :${this.hashCode()} at : $startTime".log()
            cmd.run()
            "end run :${this.hashCode()} expense : ${System.currentTimeMillis() - startTime}".log()
        }
    }
}
