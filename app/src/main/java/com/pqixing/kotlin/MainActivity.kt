package com.pqixing.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pqixing.utils.Q

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Q.init(application)
    }
}
