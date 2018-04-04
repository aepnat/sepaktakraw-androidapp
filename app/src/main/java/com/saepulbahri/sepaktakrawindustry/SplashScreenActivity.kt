package com.saepulbahri.sepaktakrawindustry

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager

class SplashScreenActivity : AppCompatActivity() {

    private val splash_timeout = 4000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // FullScreen No ActionBar
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        Handler().postDelayed(object : Runnable {
            public override fun run() {
                startActivity(Intent(this@SplashScreenActivity,MainActivity::class.java))
                finish()
            }
        },splash_timeout.toLong())
    }
}
