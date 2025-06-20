package com.example.recordkeeper.editrecord

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import androidx.core.content.edit
import androidx.navigation.fragment.findNavController
import com.example.recordkeeper.ScreenData
import com.example.recordkeeper.databinding.FragmentEditRecordBinding
import java.io.Serializable


class EditRecordFragment : Fragment() {


    private var _binding: FragmentEditRecordBinding? = null
    private val binding get() = _binding!!
    private val args: EditRecordFragmentArgs by navArgs()


    private val screenData: ScreenData by lazy { args.screenData }

    private val recordPreferences by lazy {
        requireContext().getSharedPreferences(screenData.sharedPreferencesName, Context.MODE_PRIVATE)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditRecordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        displayRecord()
    }

    private fun displayRecord() {
        binding.editTextRecord.setText(recordPreferences.getString("${screenData.record} record",null))
        binding.editTextDate.setText(recordPreferences.getString("${screenData.record} date",null))
    }




    private fun setupUI() {
        (activity as AppCompatActivity).supportActionBar?.title = "${screenData.record} Record"

        binding.buttonSave.setOnClickListener {
            saveRecord()
        }

        binding.buttonDelete.setOnClickListener {
            clearRecord()
        }

        binding.textInputRecord.hint = screenData.recordFieldHint
    }



    private fun saveRecord() {
        val record = binding.editTextRecord.text.toString()
        val date = binding.editTextDate.text.toString()

        recordPreferences.edit {
            putString("${screenData.record} record", record)
            putString("${screenData.record} date", date)
        }

        findNavController().popBackStack()
    }

    private fun clearRecord() {
        recordPreferences.edit {
            remove("${screenData.record} record")
            remove("${screenData.record} date")
        }
        findNavController().popBackStack()
    }




}