package com.example.rawgamesdb.features.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.rawgamesdb.R
import com.example.rawgamesdb.core.data.Resource
import com.example.rawgamesdb.core.utils.Constant
import com.example.rawgamesdb.databinding.FragmentLoginBinding
import com.example.rawgamesdb.features.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by viewModels ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.emailEdt.setText(Constant.emailUser)
        binding.passwordEdt.setText(Constant.passwordUser)

        loginViewModel.getToken.observe(viewLifecycleOwner){
            Log.d("TOKEN NOW", "onViewCreated: ${it.token}")
            if (!it.token.isNullOrEmpty())
                loginUser(view)
        }

        binding.signinBtn.setOnClickListener {
            loginUser(view)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun loginUser(view:View){
        loginViewModel.loginRAWGame(
            binding.emailEdt.text.toString(),
            binding.passwordEdt.text.toString()
        ).observe(requireActivity()){
            if (it != null) {
                when (it) {
                    is Resource.Loading -> {
                        showLoading()
                    }
                    is Resource.Success -> {
                        hideLoading()
                        view.findNavController().navigate(
                            R.id.action_loginFragment_to_homeFragment
                        )
                    }
                    is Resource.Error -> {
                        hideLoading()
                        Toast.makeText(
                            requireActivity(),
                            "Oops... there are some error while logging you in!",
                            Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }
    }

    private fun hideLoading(){
        binding.loadingLogin.visibility = View.GONE
        binding.signinBtn.visibility = View.VISIBLE
    }

    private fun showLoading(){
        binding.loadingLogin.visibility = View.VISIBLE
        binding.signinBtn.visibility = View.GONE
    }
}