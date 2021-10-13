package com.children.toyexchange.presentation.view.main.mainfragment.search.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.children.toyexchange.R
import com.children.toyexchange.data.models.GetStorePost
import com.children.toyexchange.databinding.SearchFragmentRecyclerviewItemBinding
import com.google.firebase.firestore.QuerySnapshot

class RecentPostsAdapter (
    private val querySnapshot : LiveData<QuerySnapshot>
) : RecyclerView.Adapter<RecentPostsViewHolder>() {
    var post : ArrayList<GetStorePost?> = arrayListOf()

    init {
        for (snapshot in querySnapshot.value!!.documents) {
            val item = snapshot.toObject(GetStorePost::class.java)
            post.add(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentPostsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<SearchFragmentRecyclerviewItemBinding>(layoutInflater, R.layout.search_fragment_recyclerview_item, parent, false)

        return RecentPostsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecentPostsViewHolder, position: Int) {
       holder.bind(post[position])
    }

    override fun getItemCount(): Int {
        return post.size
    }
}

class RecentPostsViewHolder(val binding: SearchFragmentRecyclerviewItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(data: GetStorePost?){
        binding.data = data
        binding.executePendingBindings()
    }

}