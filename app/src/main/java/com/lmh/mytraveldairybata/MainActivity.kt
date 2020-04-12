package com.lmh.mytraveldairybata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    private val MytravelDairyNew: Unit = Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnnewplan.setOnClickListener {
            val intent = Intent(this, MytravelDairyNew::class.java)
            startActivity<MyTravelDairyNew>()
        }
    }
}


