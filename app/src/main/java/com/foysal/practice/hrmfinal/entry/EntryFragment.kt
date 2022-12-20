package com.foysal.practice.hrmfinal.entry

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.foysal.practice.hrmfinal.R
import com.foysal.practice.hrmfinal.database.ExceptionData
import com.foysal.practice.hrmfinal.database.UserDatabase
import com.foysal.practice.hrmfinal.databinding.FragmentEntryBinding
import kotlinx.coroutines.*
import java.util.*

class EntryFragment : Fragment(){


    private lateinit var userName: EditText
    private lateinit var userId : EditText
    private lateinit var userExceptionType : AutoCompleteTextView
    private lateinit var userReason : EditText
    private lateinit var appBtn : Button


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val binding : FragmentEntryBinding = DataBindingUtil.inflate(inflater,
        R.layout.fragment_entry,container,false)

        val viewModelJob = Job()
        val scope = CoroutineScope(Dispatchers.Main + viewModelJob)
        val application = requireNotNull(this.activity).application
        val database = UserDatabase.getInstance(application, scope)
        val arguments = EntryFragmentArgs.fromBundle(requireArguments())

        val viewModelFactory = EntryViewModelFactory(arguments.id, arguments.role,database, application)
        val entryViewModel = ViewModelProvider(this, viewModelFactory).get(EntryViewModel::class.java)

        userName = binding.eeName
        userId = binding.eeId
        var userDate = binding.eeDate
        userExceptionType = binding.eeDropDown
        userReason = binding.eeReason
        var userStatus = "Pending"
        appBtn = binding.eeApply




        val types = resources.getStringArray(R.array.all_types)
        var calender : Calendar

        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_menu, types)

        userExceptionType.setAdapter(arrayAdapter)

        userDate.setOnClickListener {

            calender = Calendar.getInstance()
            var year = calender.get(Calendar.YEAR)
            var month = calender.get(Calendar.MONTH)
            var day = calender.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                // on below line we are passing context.
                requireContext(),
                { _, year, monthOfYear, dayOfMonth ->
                    // on below line we are setting
                    // date to our edit text.
                    val dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    userDate.setText(dat)
                },
                // on below line we are passing year, month
                // and day for the selected date in our date picker.
                year,
                month,
                day
            )
            // at last we are calling show
            // to display our date picker dialog.
            datePickerDialog.datePicker.maxDate = Date().time - 36400000
            datePickerDialog.show()

        }
        var uRole = MutableLiveData<String>()

        appBtn.setOnClickListener{
            btnAppClicked(
                userName.text.toString(),
                userId.text.toString(),
                userDate,
                userExceptionType.text.toString(),
                userStatus,
                scope,
                database,
                userReason.text.toString(),
                arguments
            )
        }
        return binding.root
    }

    private fun btnAppClicked(
        userName: String,
        userId: String,
        userDate: AppCompatEditText,
        userExceptionType: String,
        userStatus: String,
        scope: CoroutineScope,
        database: UserDatabase,
        userReason: String,
        arguments: EntryFragmentArgs
    ) {
        Toast.makeText(requireContext(), "Apply Presses", Toast.LENGTH_LONG).show()
        Log.i("UserName", "$userName")
        Log.i("UserName", "$userId")
        Log.i("UserName", "${userDate.text}")
        Log.i("UserName", "$userExceptionType")
        Log.i("UserName", "$userStatus")

        scope.launch {
            withContext(Dispatchers.IO) {
                database.exceptionDao.insert(
                    ExceptionData(
                        "$userName",
                        "$userId",
                        "${userDate.text}",
                        "$userExceptionType",
                        "$userReason",
                        "$userStatus"
                    )
                )
            }
        }
        this.findNavController().navigate(
            EntryFragmentDirections.actionEntryFragmentToHomeFragment2(userId, arguments.role)
        )
    }
}