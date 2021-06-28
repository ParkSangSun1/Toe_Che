package com.children.toyexchange.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.children.toyexchange.R
import com.children.toyexchange.databinding.ActivityMainBinding
import com.children.toyexchange.ui.view.base.BaseActivity
import com.children.toyexchange.ui.view.mainfragment.*
import com.children.toyexchange.ui.view.mainfragment.exchange.ExchangeFragment

class MainActivity : BaseActivity() {
    private val binding by binding<ActivityMainBinding>(R.layout.activity_main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {

        }
        replaceFragment(SearchFragment())



        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.search -> {
                    replaceFragment(SearchFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.exchange -> {
                    replaceFragment(ExchangeFragment())
                    return@setOnNavigationItemSelectedListener true
                }
//                R.id.shopping_basket -> {
//                    replaceFragment(ShoppingBasketFragment())
//                    return@setOnNavigationItemSelectedListener true
//                }
                R.id.chatting -> {
                    replaceFragment(ChattingFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.mypage -> {
                    replaceFragment(MypageFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    return@setOnNavigationItemSelectedListener false
                }
            }
        }

    }

    override fun onStart() {
        super.onStart()
        replaceFragment(SearchFragment())
        binding.bottomNavigation.menu.getItem(0).isChecked = true
//        supportFragmentManager.beginTransaction().replace(R.id.search,SearchFragment()).commitAllowingStateLoss()



    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.apply {
            replace(R.id.frame, fragment)
            commit()
        }
    }



}