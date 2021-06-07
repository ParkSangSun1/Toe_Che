package com.children.toyexchange.UI.Inside

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.children.toyexchange.R
import com.children.toyexchange.UI.Inside.Chatting.ChattingFragment
import com.children.toyexchange.UI.Inside.Mypage.MypageFragment
import com.children.toyexchange.UI.Inside.ProductRegistration.ProductRegistrationFragment
import com.children.toyexchange.UI.Inside.Search.SearchFragment
import com.children.toyexchange.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() , BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(this)

        supportFragmentManager.beginTransaction().add(R.id.linearLayout, SearchFragment()).commit()

        setContentView(binding.root)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.search -> {
                supportFragmentManager.beginTransaction().replace(R.id.linearLayout , SearchFragment()).commitAllowingStateLoss()
                return true
            }
            R.id.product_registration -> {
                supportFragmentManager.beginTransaction().replace(R.id.linearLayout, ProductRegistrationFragment()).commitAllowingStateLoss()
                return true
            }
            R.id.chatting -> {
                supportFragmentManager.beginTransaction().replace(R.id.linearLayout, ChattingFragment()).commitAllowingStateLoss()
                return true
            }
            R.id.mypage -> {
                supportFragmentManager.beginTransaction().replace(R.id.linearLayout, MypageFragment()).commitAllowingStateLoss()
                return true
            }
        }

        return false
    }
}