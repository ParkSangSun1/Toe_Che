package com.children.toyexchange.UI

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.animation.TranslateAnimation
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.children.toyexchange.UI.Adapter.CategoryAdapter
import com.children.toyexchange.UI.Adapter.CustomizedRecommendAdapter
import com.children.toyexchange.UI.Adapter.JustCameUpAdapter
import com.children.toyexchange.UI.MyToyUpload.ToyUploadActivity
import com.children.toyexchange.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val TAG = "로그"
    lateinit var binding: ActivityMainBinding
    private var upDownWait: Long = 0


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)


        //파란색 음명 표시 삭제
        binding.mainScrollview.overScrollMode = View.OVER_SCROLL_NEVER
        binding.customizedRecommendRecyclerview.overScrollMode = View.OVER_SCROLL_NEVER
        binding.categoryRecyclerview.overScrollMode = View.OVER_SCROLL_NEVER
        binding.justCameUpRecyclerview.overScrollMode = View.OVER_SCROLL_NEVER


        //맞춤 추천 장난감
        binding.customizedRecommendRecyclerview.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        binding.customizedRecommendRecyclerview.adapter = CustomizedRecommendAdapter()


        //카테고리
        binding.categoryRecyclerview.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        binding.categoryRecyclerview.adapter = CategoryAdapter()
        setContentView(binding.root)



        binding.mainScrollview.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->

            Log.i(TAG, "scrolly : $scrollY");

            if (System.currentTimeMillis() - upDownWait >= 200) {
                upDownWait = System.currentTimeMillis()

                Log.i(TAG, "드왔다2");

                //스크롤이 가장 위
                if (scrollY <= 10) {
                    Log.i(TAG, "TOP SCROLL");
                    val anim =
                        TranslateAnimation(binding.toyUploadBtn.width.toFloat() + 1000, 0f, 0f, 0f)
                    anim.duration = 100

                    binding.toyUploadBtn.animation = anim
                    binding.toyUploadBtn.visibility = View.VISIBLE
                }

                //스크롤 아래로
                if (scrollY > oldScrollY) {
                    Log.i(TAG, "Scroll DOWN");

                    val anim = TranslateAnimation(0f, binding.toyUploadBtn.width.toFloat(), 0f, 0f)
                    anim.duration = 100
                    binding.toyUploadBtn.animation = anim
                    binding.toyUploadBtn.visibility = View.GONE

                }
                // 스크롤 위로
                if (scrollY + 5 < oldScrollY) {
                    Log.i(TAG, "Scroll UP");
                    val anim =
                        TranslateAnimation(binding.toyUploadBtn.width.toFloat() + 1000, 0f, 0f, 0f)
                    anim.duration = 100

                    binding.toyUploadBtn.animation = anim
                    binding.toyUploadBtn.visibility = View.VISIBLE
                }


//                        binding.mainScrollview.setOnTouchListener { v, event ->
//                            when(event.action) {
//                                MotionEvent.ACTION_UP -> {
//                                    if (scrollY ==0) {
//                                        Log.i("리그", "때고나서 위");
//                                        val anim =
//                                            TranslateAnimation(binding.toyUploadBtn.width.toFloat() + 1000, 0f, 0f, 0f)
//                                        anim.duration = 100
//
//                                        binding.toyUploadBtn.animation = anim
//                                        binding.toyUploadBtn.visibility = View.VISIBLE
//                                    }
//                                }
//                                MotionEvent.ACTION_MOVE ->{
//                                    if (scrollY ==0) {
//                                        Log.i("리그", "이동중 위");
//                                        val anim =
//                                            TranslateAnimation(binding.toyUploadBtn.width.toFloat() + 1000, 0f, 0f, 0f)
//                                        anim.duration = 100
//
//                                        binding.toyUploadBtn.animation = anim
//                                        binding.toyUploadBtn.visibility = View.VISIBLE
//                                    }
//
//                                }
//                            }
//                            //리턴값은 return 없이 아래와 같이
//                            false // or false
//                        }


            }
        }


        //방금 올라온 장난감
        binding.justCameUpRecyclerview.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        binding.justCameUpRecyclerview.adapter = JustCameUpAdapter()

        binding.toyUploadBtn.setOnClickListener {
            val intent = Intent(this, ToyUploadActivity::class.java)
            startActivity(intent)
        }



        setContentView(binding.root)

    }

}