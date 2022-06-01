package com.frood.app.presentation.ui.scan

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageView
import com.canhub.cropper.options
import com.frood.app.R
import com.frood.app.databinding.FragmentScanBinding

class ScanFragment : Fragment() {

    private var _binding: FragmentScanBinding? = null
    private val binding get() = _binding!!
    private val cropImage = registerForActivityResult(CropImageContract()) { result ->
        if (result.isSuccessful) {
            // use the returned uri
            val uriContent = result.uriContent
            val uriFilePath = result.getUriFilePath(requireContext())
            binding.previewImageView.setImageURI(uriContent)// optional usage
        } else {
            // an error occurred
            val exception = result.error
        }
    }

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
        cropImage.launch(options {
            setGuidelines(CropImageView.Guidelines.ON)
                .setGuidelinesColor(R.color.white)
                .setAspectRatio(1, 1)
                .setOutputCompressFormat(Bitmap.CompressFormat.JPEG)
                .setImageSource(includeGallery = true, includeCamera = true)
                .uri
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}