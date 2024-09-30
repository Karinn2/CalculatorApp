package com.karincelik.kotlinogren

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class FirstScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_screen)

        // 3 saniye sonra MainActivity'ye geçiş yap
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@FirstScreen, MainActivity::class.java)
            startActivity(intent)
            finish() // Başlangıç ekranını kapatır
        }, 3000) // 3000 milisaniye = 3 saniye
    }
}
