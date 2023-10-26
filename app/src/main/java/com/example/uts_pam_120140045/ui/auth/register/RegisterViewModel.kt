package com.example.uts_pam_120140045.ui.auth.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import com.example.uts_pam_120140045.data.local.DataStoreManager
import com.example.uts_pam_120140045.data.local.UserData
import kotlinx.coroutines.launch

class RegisterViewModel (application: Application) : AndroidViewModel(application) {
    private val dataStore = DataStoreManager(application)

    fun saveUser(userData: UserData) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStore.saveUserToDataStore(userData)
        }
    }

    fun saveToken(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStore.saveToken(token)
        }
    }
}