package com.example.mobileclientalog.ui.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mobileclientalog.R
import com.example.mobileclientalog.data.repositories.LogInRepository
import com.example.mobileclientalog.utils.sharedPrefFile
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        start()

        constraintLayout2.setOnClickListener {

            val email = Email.text.toString()
            val password = Pass.text.toString()

            if (email.isEmpty()) {
                Email.error = "Email Required"
                Email.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                Pass.error = "Password Required"
                Pass.requestFocus()
                return@setOnClickListener
            }

            var loginActivity = LogInRepository.Companion
            loginActivity.login(this, email, password)
            // val intent = Intent(this, HomeActivity::class.java)
            // startActivity(intent)

        }
    }

    fun start() {
        val sharedPref = this.getSharedPreferences(
            sharedPrefFile, Context.MODE_PRIVATE
        )

        val con = sharedPref.getBoolean("connected",false)
        if (con){
            //val intent = Intent(this,HomeActivity::class.java)
            //startActivity(intent)
            finish()
        }

    }
}