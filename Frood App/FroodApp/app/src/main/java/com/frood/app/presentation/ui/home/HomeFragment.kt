package com.frood.app.presentation.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.frood.app.R
import com.frood.app.databinding.FragmentHomeBinding
import com.frood.app.presentation.util.adapter.HomePagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment(), HomeContract.View {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeContract.ViewModel by activityViewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.view = this
        viewModel.loadData()

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) { closePage() }

        initViewPagerFragment()

        return binding.root
    }

    private fun closePage() {
        requireActivity().finish()
    }

    override fun initViewPagerFragment() {
        val adapter = HomePagerAdapter(childFragmentManager,lifecycle)
        binding.viewPager.adapter = adapter

        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        TabLayoutMediator(binding.tabLayout,binding.viewPager){ tab, position ->
            when(position){
                0 -> {
                    tab.text = getString(R.string.title_meat)
                }
                1 -> {
                    tab.text = getString(R.string.title_fruit_vegetables)
                }
            }

        }.attach()
    }

    override fun openScanPage() {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToScanFragment())
    }

    override fun openInformationPage() {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToInformationFragment())
    }

}