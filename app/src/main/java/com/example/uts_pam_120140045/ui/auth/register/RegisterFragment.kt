package com.example.uts_pam_120140045.ui.auth.register

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.uts_pam_120140045.MainActivity
import com.example.uts_pam_120140045.R
import com.example.uts_pam_120140045.data.local.UserData
import com.example.uts_pam_120140045.databinding.FragmentRegisterBinding
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        binding.apply {
            registerButton.setOnClickListener {
                val userData = UserData(
                    username = usernameEditText.text.toString(),
                    password = passwordEditText.text.toString(),
                    githubUsername = githubUsernameEditText.text.toString(),
                    nim = nimEditText.text.toString(),
                    email = emailEditText.text.toString()
                )

                lifecycleScope.launch {
                    if (userData.username != "") {
                        viewModel.saveUser(userData)
                        viewModel.saveToken("a")
                        val intent = Intent (this@RegisterFragment.context, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(context, "Data not Valid", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            loginButton.setOnClickListener(){
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
            }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}