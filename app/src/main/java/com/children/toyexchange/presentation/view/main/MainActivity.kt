package com.children.toyexchange.presentation.view.main

import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.children.toyexchange.R
import com.children.toyexchange.presentation.base.BaseActivity
import com.children.toyexchange.presentation.widget.utils.UserInfo
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.activity.viewModels
import com.children.toyexchange.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private val binding by binding<ActivityMainBinding>(R.layout.activity_main)
    lateinit var navi: BottomNavigationView
    private lateinit var navController: NavController
    private val mainViewModel by viewModels<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        UserInfo.apply {
            Log.d("로그","로그인한 사용자의 기본 정보 \n userNickName : $userNickName \n" +
                    " userPhoneNumber : $userPhoneNumber \n" +
                    " userPhoto : $userPhoto")

        }
        initToolBar()
        initNav()


/*        replaceFragment(SearchFragment())



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
        }*/

    }

    private fun initToolBar(){
        setSupportActionBar(binding.toolbar)
    }


    private fun initNav(){
        navi = binding.bottomNavigation
        //supportActionBar!!.hide()
        navController = findNavController(R.id.frame)
        //앱 바 구성
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.searchFragment,
                R.id.exchangeFragment,
                R.id.chattingFragment,
                R.id.mypageFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navi.setupWithNavController(navController)

    }

    override fun onStart() {
        super.onStart()
   //     replaceFragment(SearchFragment())
      //  binding.bottomNavigation.menu.getItem(0).isChecked = true
//        supportFragmentManager.beginTransaction().replace(R.id.search,SearchFragment()).commitAllowingStateLoss()



    }

/*    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.apply {
            replace(R.id.frame, fragment)
            commit()
        }
    }*/
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


}