package com.yunusbalikci.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.yunusbalikci.todolist.databinding.ActivityMainBinding
import com.yunusbalikci.todolist.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var topAnim: Animation
    private lateinit var botAnim: Animation
    val SPLASH_SCREEN: Int = 4000

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_anim)
        botAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim)

        binding.imageView3.startAnimation(topAnim)
        binding.textView6.startAnimation(topAnim)
        binding.textView7.startAnimation(topAnim)

        binding.editTextEmail.startAnimation(botAnim)
        binding.editTextName.startAnimation(botAnim)
        binding.editTextPassword.startAnimation(botAnim)
        binding.editTextPassword2.startAnimation(botAnim)
        binding.buttonSubmit.startAnimation(botAnim)


        firebaseAuth = FirebaseAuth.getInstance()
        binding.buttonSubmit.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()
            val confirmpass = binding.editTextPassword2.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmpass.isNotEmpty()) {
                if (confirmpass == password) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                val intent = Intent(this@RegisterActivity, Login::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(this@RegisterActivity, it.exception.toString(), Toast.LENGTH_SHORT).show()

                            }
                        }
                } else {
                    Toast.makeText(this, "Password is not matching!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun waitAndStartActivity() {
        Handler().postDelayed({
            // Burada diğer aktiviteyi başlatmak için Intent kullanabilirsiniz
        }, SPLASH_SCREEN.toLong())
    }

}