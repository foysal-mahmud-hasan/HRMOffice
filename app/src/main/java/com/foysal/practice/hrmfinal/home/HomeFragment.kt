package com.foysal.practice.hrmfinal.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.foysal.practice.hrmfinal.R
import com.foysal.practice.hrmfinal.database.UserDatabase
import com.foysal.practice.hrmfinal.databinding.FragmentHomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding : FragmentHomeBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_home,container, false)

        val application = requireNotNull(this.activity).application

        val arguments = HomeFragmentArgs.fromBundle(requireArguments())

        if ( arguments.role == "Employee"){
            binding.hoActAut.visibility = View.INVISIBLE
        }

        Log.i("USER", "{${arguments.role}}")

        val viewModelJob = Job()

        val scope = CoroutineScope(Dispatchers.Main + viewModelJob)
        val database = UserDatabase.getInstance(application, scope)

        val viewModelFactory = HomeViewModelFactory(arguments.id,arguments.role, database, application)
        val homeViewModel = ViewModelProvider(
            this, viewModelFactory)[HomeViewModel::class.java]



        homeViewModel.userIdEntry.observe(viewLifecycleOwner, Observer { finishedEntry ->

            finishedEntry?.let {
                this.findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToEntryFragment(finishedEntry, arguments.role)
                )
                homeViewModel.doneNavigateToEntry()
            }

        })
        homeViewModel.userIdAuth.observe(viewLifecycleOwner, Observer { finishedAuth ->

            finishedAuth?.let {
                Log.i("USER", "I WAS CALLED")
                this.findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToAuthorizeFragment(finishedAuth)
                )
                homeViewModel.doneNavigateToAuth()
            }
        })

        binding.homeViewModel = homeViewModel

        return binding.root
    }

}