package com.children.toyexchange.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.TranslateAnimation
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.children.toyexchange.R
import com.children.toyexchange.databinding.ActivityMainBinding
import com.children.toyexchange.ui.adapter.CategoryAdapter
import com.children.toyexchange.ui.adapter.CustomizedRecommendAdapter
import com.children.toyexchange.ui.adapter.JustCameUpAdapter
import com.children.toyexchange.ui.myToyUpload.ToyUploadActivity


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private var upDownWait: Long = 0

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.activity = this

        //파란색 음영 표시 삭제
        shadowDelete(binding.mainScrollview, null)
        shadowDelete(null, binding.customizedRecommendRecyclerview)
        shadowDelete(null, binding.categoryRecyclerview)
        shadowDelete(null, binding.justCameUpRecyclerview)


        //맞춤 추천 장난감
        recyclerViewManager(binding.customizedRecommendRecyclerview)
        binding.customizedRecommendRecyclerview.adapter = CustomizedRecommendAdapter()


        //카테고리
        recyclerViewManager(binding.categoryRecyclerview)
        binding.categoryRecyclerview.adapter = CategoryAdapter()


        //방금 올라온 장난감
        recyclerViewManager(binding.justCameUpRecyclerview)
        binding.justCameUpRecyclerview.adapter = JustCameUpAdapter()


        //스크롤 했을때
        binding.mainScrollview.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            scrollEvent(v, scrollX, scrollY, oldScrollX, oldScrollY)
        }


//        binding.toyUploadBtn.setOnClickListener {
//            goToyUploadActivity()
//        }
//
//
//        binding.toyUploadIconBtn.setOnClickListener {
//        }


    }

    fun uploadBtn(view: View) {
        Log.i("눌림", "1")
        goToyUploadActivity()
    }

    private fun goToyUploadActivity() {
        val intent = Intent(this, ToyUploadActivity::class.java)
        startActivity(intent)
    }

    //리사이클러뷰
    private fun recyclerViewManager(v: RecyclerView) {
        v.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
    }

    //스크롤뷰, 리사이클러뷰 음영 삭제
    private fun shadowDelete(sV: NestedScrollView?, rV: RecyclerView?) {
        sV?.overScrollMode = View.OVER_SCROLL_NEVER
        rV?.overScrollMode = View.OVER_SCROLL_NEVER
    }

    //스크롤 이벤트
    private fun scrollEvent(v: View, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) {
        if (System.currentTimeMillis() - upDownWait >= 200) {
            upDownWait = System.currentTimeMillis()

            //스크롤이 가장 위
            if (scrollY <= 10) {
                val anim =
                    TranslateAnimation(binding.toyUploadBtn.width.toFloat() + 1000, 0f, 0f, 0f)
                anim.duration = 100
                binding.toyUploadBtn.animation = anim
                binding.toyUploadBtn.visibility = View.VISIBLE
            }

            //스크롤 아래로
            if (scrollY > oldScrollY) {
                val anim = TranslateAnimation(0f, binding.toyUploadBtn.width.toFloat(), 0f, 0f)
                anim.duration = 100
                binding.toyUploadBtn.animation = anim
                binding.toyUploadBtn.visibility = View.GONE
            }

            // 스크롤 위로
            if (scrollY + 5 < oldScrollY) {
                val anim =
                    TranslateAnimation(binding.toyUploadBtn.width.toFloat() + 1000, 0f, 0f, 0f)
                anim.duration = 100
                binding.toyUploadBtn.animation = anim
                binding.toyUploadBtn.visibility = View.VISIBLE
            }
        }
    }

}