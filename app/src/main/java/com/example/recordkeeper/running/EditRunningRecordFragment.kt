package com.example.recordkeeper.running

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.recordkeeper.databinding.FragmentEditRunningRecordBinding
import androidx.core.content.edit
import androidx.navigation.fragment.findNavController


class EditRunningRecordFragment : Fragment() {


    private var _binding: FragmentEditRunningRecordBinding? = null
    private val binding get() = _binding!!
    private val args: EditRunningRecordFragmentArgs by navArgs()
    private lateinit var runningPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditRunningRecordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        runningPreferences = requireContext().getSharedPreferences("running", Context.MODE_PRIVATE)
        setupToolbar()
        setupButton()
        setupFields()
    }

    private fun setupFields() {
        binding.editTextRecord.setText(runningPreferences.getString("${args.distance} record",null))
        binding.editTextDate.setText(runningPreferences.getString("${args.distance} date",null))
    }


    private fun setupToolbar() {
        val distance = args.distance
        Log.d("Distance",distance)
    }

    private fun setupButton() {
        binding.buttonSave.setOnClickListener {
            saveRecord()
        }
    }

    private fun saveRecord() {
        val record = binding.editTextRecord.text.toString()
        val date = binding.editTextDate.text.toString()

        runningPreferences.edit {
            this.putString("${args.distance} record", record)
            this.putString("${args.distance} date", date)
        }

        findNavController().popBackStack()

    }



}