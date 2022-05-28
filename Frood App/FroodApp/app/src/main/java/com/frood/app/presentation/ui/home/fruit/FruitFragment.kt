package com.frood.app.presentation.ui.home.fruit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.frood.app.R
import com.frood.app.databinding.FragmentFruitBinding
import com.frood.app.databinding.FragmentMeatBinding
import com.frood.app.domain.model.Product
import com.frood.app.presentation.ui.home.HomeContract
import com.frood.app.presentation.ui.home.HomeViewModel
import com.frood.app.presentation.util.adapter.AdapterProduct


class FruitFragment : Fragment(), FruitContract.View {

    private lateinit var binding: FragmentFruitBinding
    private val viewModel: HomeContract.ViewModel by activityViewModels<HomeViewModel>()

    private val adapterProduct = AdapterProduct()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_fruit, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.listMeat.adapter = adapterProduct

        viewModel.fruitListObservable.observe(viewLifecycleOwner){
            fetchFruitList(it)
        }

        return binding.root
    }

    override fun fetchFruitList(fruitList: List<Product>) {
        adapterProduct.submitList(fruitList)
    }


}