package com.frood.app.presentation.util.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.frood.app.presentation.ui.home.fruit.FruitFragment
import com.frood.app.presentation.ui.home.meat.MeatFragment

class HomePagerAdapter(fragment: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragment, lifecycle) {

    private var fragments: ArrayList<Fragment> = arrayListOf(
        MeatFragment(), FruitFragment()
    )


    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}