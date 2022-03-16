package com.children.toyexchange.presentation.view.main.mainfragment.chatting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.children.toyexchange.R
import com.children.toyexchange.databinding.FragmentChattingBinding
import com.children.toyexchange.presentation.base.BaseFragment
import com.children.toyexchange.presentation.widget.extension.showVertical


class ChattingFragment : BaseFragment<FragmentChattingBinding>(R.layout.fragment_chatting) {


    override fun init() {
        binding.chatRecyclerView.showVertical(requireContext())
        binding.chatRecyclerView.adapter = ChattingRecyclerAdapter()
    }
}