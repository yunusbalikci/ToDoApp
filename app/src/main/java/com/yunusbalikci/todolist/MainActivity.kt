package com.yunusbalikci.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.yunusbalikci.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val SPLASH_SCREEN: Int = 3000
    private lateinit var binding: ActivityMainBinding
    private lateinit var topAnim: Animation
    private lateinit var botAnim: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_anim)
        botAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim)

        binding.imageView2.startAnimation(topAnim)
        binding.textView.startAnimation(botAnim)
        binding.textView2.startAnimation(botAnim)
        waitAndStartActivity()

    }


  //  val fragmentManager = supportFragmentManager
    // Handler kullanarak animasyon süresini bekleyen fonksiyon
    private fun waitAndStartActivity() {
        Handler().postDelayed({
            // Burada diğer aktiviteyi başlatmak için Intent kullanabilirsiniz
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
             finish()
        }, SPLASH_SCREEN.toLong())
    }



}