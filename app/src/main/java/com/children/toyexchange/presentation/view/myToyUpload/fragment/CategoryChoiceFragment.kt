package com.children.toyexchange.presentation.view.myToyUpload.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.children.toyexchange.R
import com.children.toyexchange.databinding.FragmentCategoryChoiceBinding
import com.children.toyexchange.presentation.view.myToyUpload.ToyUploadViewModel

class CategoryChoiceFragment : Fragment() {
    private lateinit var binding: FragmentCategoryChoiceBinding
    private val toyUploadViewModel by activityViewModels<ToyUploadViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_category_choice, container, false)
        binding.fragment = this

        return binding.root
    }

    fun categoryItemClick(view: View) {
        Log.d("로그", "뷰가 어떻게 들어오나? : ${view.id}")

        //카테고리 차례대로
        when (view) {
            binding.category1 -> toyUploadViewModel.setUserChoiceCategory("신생아/ 유아")
            binding.category2 -> toyUploadViewModel.setUserChoiceCategory("액션")
            binding.category3 -> toyUploadViewModel.setUserChoiceCategory("RC조종")
            binding.category4 -> toyUploadViewModel.setUserChoiceCategory("자동차")
            binding.category5 -> toyUploadViewModel.setUserChoiceCategory("교육")
            binding.category6 -> toyUploadViewModel.setUserChoiceCategory("피규어")
            binding.category7 -> toyUploadViewModel.setUserChoiceCategory("프라모델")
            binding.category8 -> toyUploadViewModel.setUserChoiceCategory("BB탄")
            binding.category9 -> toyUploadViewModel.setUserChoiceCategory("스포츠")
            binding.category10 -> toyUploadViewModel.setUserChoiceCategory("기타")
        }
        view.findNavController().popBackStack()
    }
}