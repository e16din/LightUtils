package com.e16din.lightutils.app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.e16din.lightutils.utils.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        64.toDp

        "some text".log()

        R.string.app_name.getString()
        R.color.colorAccent.getColor()

        vHelloLabel.startOnClick(AnActivity::class.java)

        // AnActivity::class.java.start()
        // "www.yandex.ru".start()

        // todo: add examples
    }
}
