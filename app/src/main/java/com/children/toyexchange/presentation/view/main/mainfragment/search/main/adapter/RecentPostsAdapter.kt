package com.children.toyexchange.presentation.view.main.mainfragment.search.main.adapter

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.children.toyexchange.R
import com.children.toyexchange.data.models.GetStorePost
import com.children.toyexchange.databinding.SearchFragmentRecyclerviewItemBinding
import com.children.toyexchange.presentation.view.main.MainViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecentPostsAdapter(
    private val querySnapshot: LiveData<QuerySnapshot>,
    private val viewModel: MainViewModel,
    private val uid: FirebaseAuth,
    private val context: Context
) : RecyclerView.Adapter<RecentPostsViewHolder>() {
    var post: ArrayList<GetStorePost?> = arrayListOf()
    var imageIndex = 0
    var postImageArray: ArrayList<ArrayList<Uri?>> = arrayListOf()
    var postImage: ArrayList<Uri?> = arrayListOf()


    init {
        postImageArray.clear()
        for (snapshot in querySnapshot.value!!.documents) {
            val item = snapshot.toObject(GetStorePost::class.java)
            post.add(item)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentPostsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<SearchFragmentRecyclerviewItemBinding>(
            layoutInflater,
            R.layout.search_fragment_recyclerview_item,
            parent,
            false
        )

        return RecentPostsViewHolder(binding)
    }

    private fun callGetStoragePost(uid: String?, post: String?, index: Int) =
        viewModel.getStoragePost(uid, post, index)

    override fun onBindViewHolder(holder: RecentPostsViewHolder, position: Int) {
        holder.bind(post[position])

        for (index in 0 until post[position]?.photo.toString().toInt()) {

            viewModel.getStoragePost(uid.uid, post[position]?.title, index)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        postImage.add(it.result)
                        if (imageIndex == 0) Glide.with(context).load(it.result).into(holder.binding.image)
                        Log.d("로그", "이곳1 : ${postImage.size}, ${postImage[imageIndex]}")
                        ++imageIndex
                    }
                }

        }


        Log.d("로그", "photo사이즈 : , ${postImage.size}")
        postImageArray.add(postImage)
    }

    override fun getItemCount(): Int {
        return post.size
    }
}

class RecentPostsViewHolder(val binding: SearchFragmentRecyclerviewItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(data: GetStorePost?) {
        binding.data = data
        binding.executePendingBindings()
    }

}