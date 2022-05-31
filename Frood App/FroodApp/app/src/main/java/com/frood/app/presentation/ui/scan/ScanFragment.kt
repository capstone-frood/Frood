package com.frood.app.presentation.ui.scan

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.frood.app.R
import com.frood.app.databinding.FragmentScanBinding
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import java.io.File

class ScanFragment : Fragment() {

    private var _binding: FragmentScanBinding? = null
    private val binding get() = _binding!!
    private var getFile: File? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScanBinding.inflate(inflater, container, false)
        binding.selectImage.setOnClickListener {
            startCropActivity()
        }
        return binding.root
    }

    private fun startCropActivity() {
        CropImage.activity()
            .setGuidelines(CropImageView.Guidelines.ON)
            .setGuidelinesColor(R.color.white)
            .setAspectRatio(1, 1)
            .start(requireContext(), this)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result: CropImage.ActivityResult = CropImage.getActivityResult(data)
            if (resultCode == RESULT_OK) {
                val resultUri: Uri = result.uri
                binding.previewImageView.setImageURI(resultUri)
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) run {
                val e: Exception = result.error
                Log.d("error", "error")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}