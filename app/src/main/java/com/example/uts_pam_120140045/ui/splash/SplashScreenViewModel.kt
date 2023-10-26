package com.example.uts_pam_120140045.ui.splash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.Dispatchers
import com.example.uts_pam_120140045.data.local.DataStoreManager

class SplashScreenViewModel (application: Application) : AndroidViewModel(application) {
    private val dataStore = DataStoreManager(application)

    val getToken = dataStore.getToken().asLiveData(Dispatchers.IO)

}