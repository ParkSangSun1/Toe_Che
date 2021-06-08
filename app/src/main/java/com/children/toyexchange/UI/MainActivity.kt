package com.children.toyexchange.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.children.toyexchange.UI.Adapter.CustomizedRecommendAdapter
import com.children.toyexchange.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.customizedRecommendRecyclerview.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)

        binding.customizedRecommendRecyclerview.adapter = CustomizedRecommendAdapter()

        setContentView(binding.root)
    }

//    private fun initRecycler() {
//        customizedRecommendAdapter = CustomizedRecommendAdapter(this)
//        binding.customizedRecommendRecyclerview.adapter = customizedRecommendAdapter
//
//
//        datas.apply {
//            add(
//                CustomizedRecommendData(
//                    screenshot = "https://img1.daumcdn.net/thumb/R720x0.q80/?scode=mtistory2&fname=http%3A%2F%2Fcfile7.uf.tistory.com%2Fimage%2F24283C3858F778CA2EFABE".toUri(),
//                    productName = "닌자고",
//                    productPrice = 1000
//                )
//            )
//
//
////            CustomizedRecommendAdapter.datas = datas
////            CustomizedRecommendAdapter.notifyDataSetChanged()
//
//        }
//    }


}