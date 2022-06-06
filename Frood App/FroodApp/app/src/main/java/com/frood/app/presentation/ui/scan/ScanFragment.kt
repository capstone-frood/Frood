package com.frood.app.presentation.ui.scan

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageView
import com.canhub.cropper.options
import com.frood.app.R
import com.frood.app.api.ApiConfig
import com.frood.app.api.PredictResponse
import com.frood.app.databinding.FragmentScanBinding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class ScanFragment : Fragment() {

    private var _binding: FragmentScanBinding? = null
    private val binding get() = _binding!!
    private var getFile: File? = null
    private val cropImage = registerForActivityResult(CropImageContract()) { result ->
        if (result.isSuccessful) {
            // use the returned uri
            val uriContent = result.uriContent
            val uriFilePath = result.getUriFilePath(requireContext())
            Log.d(TAG, uriContent.toString())
            requireActivity().intent.putExtra("pict", uriFilePath)
            getFile = uriContent?.let { uriToFile(it, requireContext()) }
            binding.previewImageView.setImageURI(uriContent)// optional usage
        } else {
            // an error occurred
            val exception = result.error
        }
    }

    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
        const val TAG = "TAG"
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.no_permission),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScanBinding.inflate(inflater, container, false)
        showLoading(false)
        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }
        binding.selectImage.setOnClickListener {
            startCropActivity()
        }
        binding.imageNext.setOnClickListener {
            showLoading(true)
            startSendPicture()
        }
        binding.imageClose.setOnClickListener {
            closeScan()
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

    private fun startSendPicture() {
        if (getFile != null) {
            val file = reduceFileImage(getFile as File)
            val requestImageFile = file.asRequestBody("image.jpg".toMediaTypeOrNull())
            val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                "image",
                file.name,
                requestImageFile
            )
            postPredict(imageMultipart)
        } else {
            AlertDialog.Builder(requireContext()).apply {
                setTitle(getString(R.string.upload_error))
                setMessage(getString(R.string.no_photo))
                setPositiveButton("OK") { _, _ ->
                }
                create()
                show()
            }
        }
    }

    private fun postPredict(picture: MultipartBody.Part) {
        showLoading(true)
        val client = ApiConfig.getApiService().predict(picture)
        client.enqueue(object : Callback<PredictResponse> {
            override fun onResponse(
                call: Call<PredictResponse>,
                response: Response<PredictResponse>
            ) {
                showLoading(false)
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    Log.e(TAG, "onSuccess: ${response.message()}")
                    requireActivity().intent.putExtra("status", responseBody.status)
                    requireActivity().intent.putExtra("note", responseBody.note)
                    requireActivity().intent.putExtra("expiry", responseBody.expired)
                    findNavController().navigate(R.id.action_scanFragment_to_resultFragment)
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<PredictResponse>, t: Throwable) {
                showLoading(false)
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    private fun closeScan() {
        findNavController().navigate(R.id.action_scanFragment_to_homeFragment)
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}