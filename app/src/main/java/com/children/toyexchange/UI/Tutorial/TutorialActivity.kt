package com.children.toyexchange.UI.Tutorial

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.children.toyexchange.UI.Tutorial.Adapter.TutorialViewPagerAdapter
import com.children.toyexchange.databinding.ActivityTutorialBinding

class TutorialActivity : AppCompatActivity() {




    private val bgColors = android.R.color.white
    private lateinit var binding: ActivityTutorialBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorialBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.pager.adapter = TutorialViewPagerAdapter(bgColors)
        binding.pager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    }
}