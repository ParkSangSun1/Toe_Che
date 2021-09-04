package com.children.toyexchange.presentation.view.main.mainfragment.search

import android.os.Bundle
import com.children.toyexchange.R
import com.children.toyexchange.databinding.ActivitySearchRecordBinding
import com.children.toyexchange.presentation.base.BaseActivity

class SearchRecordActivity : BaseActivity() {
    private val binding by binding<ActivitySearchRecordBinding>(R.layout.activity_search_record)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_record)
    }


}