package com.example.nemopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.nemopedia.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Sembunyikan action bar
        supportActionBar?.hide()

        // Jalankan animasi
        startAnimations()

        // Delay 1.5 detik lalu pindah ke MainActivity
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 1500)
    }

    private fun startAnimations() {
        // Load animasi fade in
        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)

        // Terapkan animasi ke semua elemen
        binding.apply {
            tvAppIcon.startAnimation(fadeIn)
            tvAppName.startAnimation(fadeIn)
            tvTagline.startAnimation(fadeIn)
            progressBar.startAnimation(fadeIn)
        }
    }
}