package com.example.recordkeeper.cycling

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.recordkeeper.ScreenData
import com.example.recordkeeper.databinding.FragmentCyclingBinding
import com.example.recordkeeper.editrecord.EditRecordFragment


class CyclingFragment : Fragment() {

    private var _binding: FragmentCyclingBinding? = null
    private val binding get() = _binding!!

    private lateinit var cyclingPreferences: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCyclingBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cyclingPreferences = requireContext().getSharedPreferences("cycling", Context.MODE_PRIVATE)

        setupClickListeners()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        displayRecords()
    }

    fun displayRecords() {
        binding.textLongestRideValue.text =  cyclingPreferences.getString("Longest Ride record", "No record set")
        binding.textLongestRideDate.text =  cyclingPreferences.getString("Longest Ride date", "No date set")

        binding.textBiggestClimbValue.text =  cyclingPreferences.getString("Biggest Climb record", "No record set")
        binding.textBiggestClimbDate.text =  cyclingPreferences.getString("Biggest Climb date", "No date set")

        binding.textBestAverageSpeedValue.text =  cyclingPreferences.getString("Best Average Speed record", "No record set")
        binding.textBestAverageSpeedDate.text =  cyclingPreferences.getString("Best Average Speed date", "No date set")
    }

    private fun setupClickListeners() {
        binding.containerLongestRide.setOnClickListener {
            launchRunningRecordScreen("Longest Ride", "Distance")
        }

        binding.containerBiggestClimb.setOnClickListener {
            launchRunningRecordScreen("Biggest Climb", "Height")
        }

        binding.containerBestAverageSpeed.setOnClickListener {
            launchRunningRecordScreen("Best Average Speed", "Average")
        }
    }

    private fun launchRunningRecordScreen(record: String, recordFieldHint: String) {
        val action = CyclingFragmentDirections.actionCyclingFragmentToEditRecordFragment(
            screenData = ScreenData(
            record, "cycling",recordFieldHint
        )
        )
        findNavController().navigate(action)
    }

}