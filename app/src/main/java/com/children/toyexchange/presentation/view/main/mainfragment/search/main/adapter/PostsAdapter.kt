package com.children.toyexchange.presentation.view.main.mainfragment.search.main.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.children.toyexchange.R
import com.children.toyexchange.data.models.Post
import com.children.toyexchange.databinding.SearchFragmentRecyclerviewItemBinding
import com.children.toyexchange.presentation.view.main.MainViewModel
import com.children.toyexchange.presentation.widget.utils.Utils.TAG

class PostsAdapter(
    private val mainViewModel: MainViewModel,
    private val context: Context
) : RecyclerView.Adapter<PostsViewHolder>() {
    val postList : ArrayList<Post> = mainViewModel.postList


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<SearchFragmentRecyclerviewItemBinding>(
            layoutInflater,
            R.layout.search_fragment_recyclerview_item,
            parent,
            false
        )
        return PostsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        Log.d(TAG,"$position, ${mainViewModel.firstImgList}")
        holder.bind(postList[position])
        holder.binding.image
        Glide.with(context).load(mainViewModel.firstImgList[position]).into(holder.binding.image)
    }

    override fun getItemCount(): Int {
        return postList.size
    }
}

class PostsViewHolder(val binding: SearchFragmentRecyclerviewItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(data: Post?) {
        binding.data = data
        binding.executePendingBindings()
    }

}