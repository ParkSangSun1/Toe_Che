package com.children.toyexchange.view.mainfragment.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.children.toyexchange.R
import com.children.toyexchange.databinding.ActivityMainBinding
import com.children.toyexchange.databinding.ActivitySearchRecordBinding
import com.children.toyexchange.view.base.BaseActivity

class SearchRecordActivity : BaseActivity() {
    private val binding by binding<ActivitySearchRecordBinding>(R.layout.activity_search_record)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_record)
    }


}