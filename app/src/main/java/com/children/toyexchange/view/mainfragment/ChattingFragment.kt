package com.children.toyexchange.view.mainfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.children.toyexchange.R
import com.children.toyexchange.databinding.FragmentChattingBinding
import com.children.toyexchange.utils.MainObject
import com.children.toyexchange.view.adapter.recycler_view.chattingfragment.ChattingRecyclerAdapter



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

        MainObject.recyclerViewManager(binding.chatRecyclerView,requireContext())
        binding.chatRecyclerView.adapter = ChattingRecyclerAdapter()


        return binding.root
    }


}