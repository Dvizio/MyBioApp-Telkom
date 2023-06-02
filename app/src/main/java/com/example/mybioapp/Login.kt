package com.example.mybioapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class Login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth


        val registerBTN: Button = findViewById(R.id.btn_register)
        registerBTN.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        val loginBTN:Button = findViewById(R.id.btn_login)
        loginBTN.setOnClickListener {
            performLogin()
        }
    }

    private fun performLogin(){
        val inputEmail = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val inputPassword = findViewById<EditText>(R.id.editTextTextPassword)

        if(inputEmail.text.isEmpty() || inputPassword.text.isEmpty()){
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val email = inputEmail.text.toString()
        val password = inputPassword.text.toString()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){ task ->
                if (task.isSuccessful){
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                    Toast.makeText(baseContext, "Success", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(baseContext, "Authentication failed",
                        Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Authentication Failed ${it.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
    }
}