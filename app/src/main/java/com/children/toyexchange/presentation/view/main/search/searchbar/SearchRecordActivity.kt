package com.children.toyexchange.presentation.view.main.search.searchbar

import android.view.View
import com.children.toyexchange.R
import com.children.toyexchange.databinding.ActivitySearchRecordBinding
import com.children.toyexchange.presentation.base.BaseActivity

class SearchRecordActivity : BaseActivity<ActivitySearchRecordBinding>(R.layout.activity_search_record) {


    override fun init() {
        binding.activity = this
    }

    fun backBtnClick(view:View){
        finish()
    }
}