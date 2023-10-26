package com.example.uts_pam_120140045.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.uts_pam_120140045.data.local.UserData
import com.example.uts_pam_120140045.databinding.FragmentProfileBinding
import com.example.uts_pam_120140045.ui.auth.AuthActivity
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        super.onViewCreated(view, savedInstanceState)

        val textUsernameUser: TextView = binding.textUsername
        val textGithubUsernameUser: TextView = binding.textGithubUsername
        val textNimUser: TextView = binding.textNim
        val textEmailUser: TextView = binding.textEmail

        lifecycleScope.launch {
            viewModel.getUser.observe(viewLifecycleOwner) { userData: UserData ->
                textUsernameUser.text = userData.username
                textGithubUsernameUser.text = userData.githubUsername
                textNimUser.text = userData.nim
                textEmailUser.text = userData.email
            }
        }

        binding.logoutButton.setOnClickListener(){
            viewModel.saveToken("")
            val intent = Intent(this@ProfileFragment.context, AuthActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}