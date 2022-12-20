package com.foysal.practice.hrmfinal.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.WorkerThread
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.foysal.practice.hrmfinal.R
import com.foysal.practice.hrmfinal.database.UserDao
import com.foysal.practice.hrmfinal.database.UserData
import com.foysal.practice.hrmfinal.database.UserDatabase
import com.foysal.practice.hrmfinal.databinding.FragmentLoginBinding
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.*

@Suppress("UNREACHABLE_CODE")
class LoginFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val binding : FragmentLoginBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_login,container, false)

        val application = requireNotNull(this.activity).application
        val scope = CoroutineScope(Dispatchers.Main)
        val dataSource = UserDatabase.getInstance(application, scope)
        val viewModelFactory = LoginViewModelFactory(dataSource, application)
        val loginViewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]

        binding.loginViewModel = loginViewModel
        binding.lifecycleOwner = this

        loginViewModel.navigateToHome.observe(viewLifecycleOwner, Observer { hasFinished ->
            hasFinished?.let {
                Log.i("USER", "ME CALLED")
                this.findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToHomeFragment(hasFinished.userId, hasFinished.userRole)
                )
                loginViewModel.doneNavigatingToHome()
            }
        })
        return binding.root

    }

//    private fun navigateToHome() {
//        val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
//        NavHostFragment.findNavController(this).navigate(action)
//    }
}


