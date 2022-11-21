package com.nithi.securityauthapp.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import com.nithi.securityauthapp.databinding.ActivitySignupBinding
import com.nithi.securityauthapp.utility.ResponseState
import com.nithi.securityauthapp.utility.showToast
import com.nithi.securityauthapp.utility.startTransaction
import com.nithi.securityauthapp.view.viewmodel.AuthViemodel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.M)
class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    private val viewModel by viewModels<AuthViemodel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSignUp.setOnClickListener {
            val confirmPassword = binding.editConfirmPassword.text.toString()
            val password = binding.editPassword.text.toString()
            val userName = binding.editUserName.text.toString()
            val name = binding.editName.text.toString()
            saveUserData(name, userName, password, confirmPassword)
        }

        lifecycleScope.launch {
            viewModel.userData.collect{
                when(it){
                    is ResponseState.Error -> {}
                    is ResponseState.Failed -> {

                    }
                    is ResponseState.Success ->{
                        startActivity(Intent(this@SignupActivity,LoginActivity::class.java))
                        finish()
                    }
                   else->{}
                }
            }
        }



    }


    private fun saveUserData(
        name: String,
        userName: String,
        password: String,
        confirmPassword: String
    ) {
        if (name.isEmpty()) {
            showToast("Name Not Empty");
            return
        }
        if (userName.isEmpty()) {
           showToast("User Name Not Empty");
            return
        }
        if (password.isEmpty()) {
            showToast("Password Not Empty");
            return
        }
        if (confirmPassword.isEmpty()) {
          showToast("Confirm Password Not Empty");
            return
        }

        if (password == confirmPassword) {
            viewModel.saveUserDetail(name, userName, password)
        } else {
            showToast(" Password Not Match")
        }
    }

}