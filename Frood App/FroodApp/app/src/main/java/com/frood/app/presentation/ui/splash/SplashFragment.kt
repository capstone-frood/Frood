package com.frood.app.presentation.ui.splash

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.frood.app.R
import com.frood.app.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_splash, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        Handler().postDelayed({
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
        },2000)

        return binding.root
    }
}