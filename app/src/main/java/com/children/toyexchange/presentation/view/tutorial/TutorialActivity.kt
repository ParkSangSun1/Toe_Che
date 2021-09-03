package com.children.toyexchange.presentation.view.tutorial

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.children.toyexchange.R
import com.children.toyexchange.databinding.ActivityTutorialBinding
import com.children.toyexchange.presentation.view.tutorial.Adapter.TutorialViewPagerAdapter

class TutorialActivity : AppCompatActivity() {




    private val bgColors = android.R.color.white
    private lateinit var binding: ActivityTutorialBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tutorial)
        setContentView(binding.root)
        binding.pager.adapter = TutorialViewPagerAdapter(bgColors)
        binding.pager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    }
}