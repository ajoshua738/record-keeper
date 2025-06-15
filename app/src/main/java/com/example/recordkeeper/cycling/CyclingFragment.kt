package com.example.recordkeeper.cycling

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.recordkeeper.databinding.FragmentCyclingBinding


class CyclingFragment : Fragment() {

    private var _binding: FragmentCyclingBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCyclingBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupClickListeners() {
        binding.containerLongestRide.setOnClickListener {
            launchRunningRecordScreen("Longest Ride")
        }

        binding.containerBiggestClimb.setOnClickListener {
            launchRunningRecordScreen("Biggest Climb")
        }

        binding.containerBestAverageSpeed.setOnClickListener {
            launchRunningRecordScreen("Best Average Speed")
        }


    }

    private fun launchRunningRecordScreen(record: String) {
        val action = CyclingFragmentDirections.actionCyclingFragmentToEditCyclingRecordFragment(record = record)
        findNavController().navigate(action)
    }

}