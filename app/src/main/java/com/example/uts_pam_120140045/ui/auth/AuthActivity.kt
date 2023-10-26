package com.example.uts_pam_120140045.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uts_pam_120140045.R

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        supportActionBar?.hide()
    }
}