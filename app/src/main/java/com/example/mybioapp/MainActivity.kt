package com.example.mybioapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val logoutBTN: Button = findViewById(R.id.btn_logout)
        logoutBTN.setOnClickListener {
            Firebase.auth.signOut()
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        val welcomeText : TextView = findViewById(R.id.textView)
        val user = Firebase.auth.currentUser
        val welcomeUser = user?.email.toString()

        welcomeText.text = getString(R.string.welcome_messages, welcomeUser)
    }
}