package com.example.androidproject

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidproject.databinding.ActivityLoginBinding
import com.example.androidproject.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LogInActivity : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //initialize firebase auth
        auth=FirebaseAuth.getInstance()



        binding.signupButton.setOnClickListener{
            startActivity(Intent(this,SignUpActivity::class.java))
            finish()
        }

        binding.loginButton.setOnClickListener{
            val userName=binding.userName.text.toString()
            val password=binding.password.text.toString()

            if (userName.isEmpty()||password.isEmpty()){
                Toast.makeText(this, "Please Fill all the details", Toast.LENGTH_SHORT).show()
            }
            else{
                auth.signInWithEmailAndPassword(userName,password)
                    .addOnCompleteListener{task->
                        if (task.isSuccessful){
                            Toast.makeText(this, "Sign-in Successful", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this,ScrollViewActivity::class.java))
                            finish()
                        }
                        else{
                            Toast.makeText(this, "Sign-in failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

    }
}