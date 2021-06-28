package com.children.toyexchange.ui.view.adapter.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.children.toyexchange.ui.view.mainfragment.exchange.viewpager.GetExchangeListFragment
import com.children.toyexchange.ui.view.mainfragment.exchange.viewpager.SetExchangeListFragment

class ExchangeListAdapter (fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                GetExchangeListFragment()
            }
            1 -> {
                SetExchangeListFragment()
            }

            else -> {
                Fragment()
            }

        }
    }
}