package com.yunusbalikci.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.yunusbalikci.todolist.databinding.ActivityLoginBinding
import com.yunusbalikci.todolist.databinding.ActivityMainBinding

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var topAnim: Animation
    private lateinit var botAnim: Animation
    private lateinit var firebaseAuth: FirebaseAuth
    val SPLASH_SCREEN: Int = 4000


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.button.setOnClickListener {
            val email :String = binding.textInputLayout.editText?.text.toString()
            val password = binding.textInputLayout2.editText?.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful){
                        val intent = Intent(this@Login,ToDoActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this, "Empty Fields Are Not Allowed!", Toast.LENGTH_SHORT).show()
            }

        }


        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_anim)
        botAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim)

        binding.imageView.startAnimation(topAnim)
        binding.textView3.startAnimation(topAnim)
        binding.textView4.startAnimation(topAnim)
        binding.textView5.startAnimation(topAnim)
        binding.textInputLayout.startAnimation(botAnim)
        binding.textInputLayout2.startAnimation(botAnim)
        waitAndStartActivity()

        binding.textViewRegister.setOnClickListener {
            intent = Intent(this@Login,RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun waitAndStartActivity() {
        Handler().postDelayed({
            // Burada diğer aktiviteyi başlatmak için Intent kullanabilirsiniz
        }, SPLASH_SCREEN.toLong())
    }




}