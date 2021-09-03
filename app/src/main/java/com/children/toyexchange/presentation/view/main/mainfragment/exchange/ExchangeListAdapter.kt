package com.children.toyexchange.presentation.view.main.mainfragment.exchange

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.children.toyexchange.presentation.view.main.mainfragment.exchange.getexchange.GetExchangeListFragment
import com.children.toyexchange.presentation.view.main.mainfragment.exchange.setexchange.SetExchangeListFragment

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