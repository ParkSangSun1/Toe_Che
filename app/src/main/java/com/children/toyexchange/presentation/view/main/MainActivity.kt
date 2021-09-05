package com.children.toyexchange.presentation.view.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.children.toyexchange.R
import com.children.toyexchange.databinding.ActivityMainBinding
import com.children.toyexchange.presentation.view.main.mainfragment.chatting.ChattingFragment
import com.children.toyexchange.presentation.view.main.mainfragment.mypage.MypageFragment
import com.children.toyexchange.presentation.base.BaseActivity
import com.children.toyexchange.presentation.view.main.mainfragment.exchange.ExchangeFragment
import com.children.toyexchange.presentation.view.main.mainfragment.search.SearchFragment
import com.children.toyexchange.presentation.widget.utils.UserInfo

class MainActivity : BaseActivity() {
    private val binding by binding<ActivityMainBinding>(R.layout.activity_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        UserInfo.apply {
            Log.d("로그","로그인한 사용자의 기본 정보 \n userNickName : $userNickName \n" +
                    " userPhoneNumber : $userPhoneNumber \n" +
                    " userPhoto : $userPhoto")

        }
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