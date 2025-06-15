package com.example.recordkeeper.cycling

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.recordkeeper.databinding.FragmentEditCyclingRecordBinding


class EditCyclingRecordFragment : Fragment() {

    private var _binding: FragmentEditCyclingRecordBinding? = null
    private val binding get() = _binding!!
    private val args: EditCyclingRecordFragmentArgs by navArgs()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditCyclingRecordBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
    }

    private fun setupToolbar() {
        val record = args.record
        Log.d("Record",record)
    }


}