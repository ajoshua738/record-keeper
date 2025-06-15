package com.example.recordkeeper.running

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.recordkeeper.databinding.FragmentRunningBinding


class RunningFragment : Fragment() {


    private var _binding: FragmentRunningBinding? = null
    private val binding get() = _binding!!
    private lateinit var runningPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRunningBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        runningPreferences = requireContext().getSharedPreferences("running", Context.MODE_PRIVATE)
        setupClickListeners()
    }

    private fun displayRecords() {


        binding.text5kmValue.text =  runningPreferences.getString("5km record", "No record set")
        binding.text5kmDate.text =  runningPreferences.getString("5km date", "No date set")

        binding.text10kmValue.text =  runningPreferences.getString("10km record", "No record set")
        binding.text10kmDate.text =  runningPreferences.getString("10km date", "No date set")

        binding.textHalfMarathonValue.text =  runningPreferences.getString("Half Marathon record", "No record set")
        binding.textHalfMarathonDate.text =  runningPreferences.getString("Half Marathon date", "No date set")

        binding.textMarathonValue.text =  runningPreferences.getString("Marathon record", "No record set")
        binding.textMarathonDate.text =  runningPreferences.getString("Marathon date", "No date set")
    }

    override fun onResume() {
        super.onResume()
        displayRecords()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupClickListeners() {
        binding.container5km.setOnClickListener {
            launchRunningRecordScreen("5km")
        }

        binding.container10km.setOnClickListener {
            launchRunningRecordScreen("10km")
        }

        binding.containerHalfMarathon.setOnClickListener {
            launchRunningRecordScreen("Half Marathon")
        }

        binding.containerMarathon.setOnClickListener {
            launchRunningRecordScreen("Marathon")
        }
    }

    private fun launchRunningRecordScreen(distance: String) {
        val action = RunningFragmentDirections.actionRunningFragmentToEditRunningRecordFragment(distance = distance)
        findNavController().navigate(action)
    }


}