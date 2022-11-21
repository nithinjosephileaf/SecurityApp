package com.nithi.securityauthapp.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import com.nithi.securityauthapp.R
import com.nithi.securityauthapp.databinding.ActivityLoginBinding
import com.nithi.securityauthapp.utility.ResponseState
import com.nithi.securityauthapp.utility.showToast
import com.nithi.securityauthapp.view.viewmodel.AuthViemodel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private  val viewModel by viewModels<AuthViemodel>()
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSignUp.setOnClickListener {
            val userName = binding.userName.text.toString()
            val password = binding.password.text.toString()
            viewModel.checkUser(userName, password)

        }
        binding.signupText.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }

        lifecycleScope.launch {
            viewModel.userData.collect{
                when(it){
                    is ResponseState.Error -> {}
                    is ResponseState.Failed -> {

                    }
                    is ResponseState.Success ->{
                        if (it.data){
                            startActivity(Intent(this@LoginActivity,MainActivity::class.java))
                            finish()
                        }else{
                            showToast("Invalid user name and password")
                        }

                    }
                    else->{}
                }
            }
        }
    }
}