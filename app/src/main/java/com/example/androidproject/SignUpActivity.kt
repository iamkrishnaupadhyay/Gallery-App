package com.example.androidproject

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidproject.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private val binding:ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //initializie firebase auth
        auth=FirebaseAuth.getInstance()


        binding.signinButton.setOnClickListener{
            startActivity(Intent(this,LogInActivity::class.java))
            finish()
        }

        binding.registerButton.setOnClickListener{
            //get text from edit tect field from sign up screen
            val email=binding.email.text.toString()
            val userName=binding.userName.text.toString()
            val password=binding.password.text.toString()
            val repeatPassword=binding.rePassword.text.toString()


            // check if any field is empty
            if (email.isEmpty()||userName.isEmpty()||password.isEmpty()||repeatPassword.isEmpty()){
                Toast.makeText(this, "Please Fill all the details", Toast.LENGTH_SHORT).show()
            }
            else if (password!=repeatPassword){
                Toast.makeText(this, "Repeat password Must be Same", Toast.LENGTH_SHORT).show()
            }
            else{
                auth.createUserWithEmailAndPassword(email,password)

                    .addOnCompleteListener(this){task ->
                        if (task.isSuccessful){
                            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this,LogInActivity::class.java))
                            finish()
                        }
                        else{
                            Toast.makeText(this, "Registration failed : ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            }




        }
    }
}