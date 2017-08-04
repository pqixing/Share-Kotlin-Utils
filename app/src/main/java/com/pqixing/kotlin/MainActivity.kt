package com.pqixing.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pqixing.utils.Q
import com.pqixing.utils.log
import com.pqixing.utils.show
import com.pqixing.utils.utils.Share
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val str by lazy { "" }
    val str2 = lazy { "" }
    val KEY_LOGIN_TIMES = "key_login_times"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Q.init(application)

        var times by Share(KEY_LOGIN_TIMES, 0)
        "这是第${times++} 次登录".log().show()

        arrayListOf(1, 2)
                .filter { it == 2 }
                .map { it to it + 2 }
                .subList(0, 1)
                .firstOrNull { it.second == 4 }
                ?.second ?: return.toString().log()
        textView.text = "0000"


        str.log()
        str2.value.log()
    }

}
