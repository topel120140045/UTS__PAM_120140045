package com.example.uts_pam_120140045.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.ViewModelProvider
import com.example.uts_pam_120140045.MainActivity
import com.example.uts_pam_120140045.ui.auth.AuthActivity
import com.example.uts_pam_120140045.R
import kotlinx.coroutines.*

class SplashScreenActivity : AppCompatActivity() {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private lateinit var viewModel: SplashScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        viewModel = ViewModelProvider(this@SplashScreenActivity)[SplashScreenViewModel::class.java]

        coroutineScope.launch {
            delay(SPLASH_DELAY_MS)

            checkLoginStatus()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }

    private fun checkLoginStatus() {
        lifecycleScope.launch {
            viewModel.getToken.observe(this@SplashScreenActivity) { token ->
                println(token)
                if(token != ""){
                    navigateToMainActivity()
                }else{
                    navigateToAuthActivity()
                }
            }
        }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun navigateToAuthActivity() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object {
        private const val SPLASH_DELAY_MS = 2000L
    }

}