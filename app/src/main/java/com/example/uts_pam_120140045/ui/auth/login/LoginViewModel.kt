package com.example.uts_pam_120140045.ui.auth.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import com.example.uts_pam_120140045.data.local.DataStoreManager
import kotlinx.coroutines.launch

class LoginViewModel (application: Application) : AndroidViewModel(application) {
    private val dataStore = DataStoreManager(application)

    val getUser = dataStore.getUserFromDataStore().asLiveData(Dispatchers.IO)

    fun saveToken(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStore.saveToken(token)
        }
    }
}