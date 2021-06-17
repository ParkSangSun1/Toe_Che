package com.children.toyexchange.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.children.toyexchange.R
import com.children.toyexchange.databinding.ActivityMain2Binding
import com.children.toyexchange.ui.view.mainfragment.*

class MainActivity2 : BaseActivity() {
    private val binding by binding<ActivityMain2Binding>(R.layout.activity_main2)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {

        }

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.search -> {
                    replaceFragment(SearchFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.product_registration -> {
                    replaceFragment(ProductRegistrationFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.shopping_basket -> {
                    replaceFragment(ShoppingBasketFragment())
                    return@setOnNavigationItemSelectedListener true
                }
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

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.apply {
            replace(R.id.frame, fragment)
            commit()
        }
    }

//    fun clickBottomNav(view: View){
//
//    }
}