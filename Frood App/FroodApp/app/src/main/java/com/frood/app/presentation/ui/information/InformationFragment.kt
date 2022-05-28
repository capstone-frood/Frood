package com.frood.app.presentation.ui.information

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.frood.app.R
import com.frood.app.databinding.FragmentInformationBinding
import com.frood.app.domain.model.Indicator
import com.frood.app.domain.model.Information
import com.frood.app.presentation.util.adapter.AdapterIndicator
import com.frood.app.presentation.util.adapter.InformationPagerAdapter

class InformationFragment : Fragment(), InformationContract.View {

    private lateinit var binding: FragmentInformationBinding
    private val presenter: InformationContract.ViewModel by viewModels<InformationViewModel>()

    private val adapterPager = InformationPagerAdapter()
    private val adapterIndicator = AdapterIndicator()

    private val pagerCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            presenter.setInformationPosition(position)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_information, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.view = this
        presenter.setInformationList()

        //pager
        binding.viewPager.adapter = adapterPager
        binding.viewPager.registerOnPageChangeCallback(pagerCallback)

        //indicator
        binding.listIndicator.adapter = adapterIndicator

        presenter.informationListObservable.observe(viewLifecycleOwner) { showInformationList(it) }
        presenter.indicatorListObservable.observe(viewLifecycleOwner) {
            showIndicatorList(it)
        }

        return binding.root
    }

    override fun onDestroyView() {
        binding.viewPager.unregisterOnPageChangeCallback(pagerCallback)
        super.onDestroyView()
    }

    override fun showInformationList(informations: List<Information>) {
        adapterPager.submitList(informations)
    }

    override fun showIndicatorList(indicators: List<Indicator>) {
        adapterIndicator.submitList(indicators)
    }

    override fun openHomePage() {
        findNavController().navigateUp()
    }

    override fun openScanPage() {
        findNavController().navigate(InformationFragmentDirections.actionInformationFragmentToScanFragment())
    }


}