package com.frood.app.presentation.ui.scan

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.frood.app.R
import com.frood.app.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        val pict = requireActivity().intent.getStringExtra("pict")
        binding.previewImageView.setImageURI(Uri.parse(pict))
        val status = requireActivity().intent.getStringExtra("status")
        val note = requireActivity().intent.getStringExtra("note")
        val expiry = requireActivity().intent.getStringExtra("expiry")
        val detectStatus =
            getString(R.string.status) + " " + status + "\n" +
                    getString(R.string.expiry) + " " + expiry + "\n" +
                    getString(R.string.note) + " " + note
        binding.predictStatus.text = detectStatus
        binding.imageClose.setOnClickListener {
            closeScan()
        }
        return binding.root
    }

    private fun closeScan() {
        findNavController().navigate(R.id.action_resultFragment_to_homeFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}