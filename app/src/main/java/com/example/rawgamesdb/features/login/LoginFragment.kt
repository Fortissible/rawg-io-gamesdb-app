package com.example.rawgamesdb.features.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.rawgamesdb.R
import com.example.rawgamesdb.core.data.Resource
import com.example.rawgamesdb.core.ui.ViewModelFactory
import com.example.rawgamesdb.core.utils.Constant
import com.example.rawgamesdb.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by viewModels {
        ViewModelFactory.getInstance(requireActivity())
    }

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

        loginUser(view,false)

        binding.signinBtn.setOnClickListener {
            loginUser(view,true)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun loginUser(view:View, isButtonClicked:Boolean){
        loginViewModel.loginRAWGame(
            binding.emailEdt.text.toString(),
            binding.passwordEdt.text.toString(),
            isButtonClicked
        ).observe(requireActivity()){
            if (it != null) {
                when (it) {
                    is Resource.Loading -> {
                        showLoading()
                    }
                    is Resource.Success -> {
                        hideLoading()
                        if (it.data?.token.isNullOrEmpty()) {
                            Toast.makeText(
                                requireActivity(),
                                "Failed to login!",
                                Toast.LENGTH_SHORT).show()
                        } else {
                            view.findNavController().navigate(
                                R.id.action_loginFragment_to_homeFragment
                            )
                        }
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