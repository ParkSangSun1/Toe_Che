package com.children.toyexchange.presentation.view.main.mainfragment.chatting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.children.toyexchange.R
import com.children.toyexchange.databinding.FragmentChattingBinding
import com.children.toyexchange.presentation.widget.extension.showVertical


class ChattingFragment : Fragment() {

    lateinit var binding : FragmentChattingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_chatting,container,false)
        binding.chatRecyclerView.showVertical(requireContext())
        binding.chatRecyclerView.adapter = ChattingRecyclerAdapter()


        return binding.root
    }


}