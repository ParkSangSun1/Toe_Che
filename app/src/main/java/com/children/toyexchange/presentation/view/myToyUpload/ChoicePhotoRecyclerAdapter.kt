package com.children.toyexchange.presentation.view.myToyUpload

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.children.toyexchange.R
import com.children.toyexchange.presentation.view.myToyUpload.MainToyUploadFragment.Companion.photoIndex

class ChoicePhotoRecyclerAdapter(private val viewModel : ToyUploadViewModel) : RecyclerView.Adapter<ChoicePhotoRecyclerAdapter.ChoicePhotoRecyclerViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChoicePhotoRecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem =
            layoutInflater.inflate(R.layout.choice_photo_recycler_view_item, parent, false)

        return ChoicePhotoRecyclerViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: ChoicePhotoRecyclerViewHolder, position: Int) {
        if (photoIndex!=0){
            holder.view.findViewById<ImageView>(R.id.recycler_image).setImageURI(viewModel.saveChoicePhoto.value?.get(position))
        }

        //사진 삭제 버튼 클릭시
        holder.view.findViewById<ImageView>(R.id.del_btn).setOnClickListener {
            viewModel.deleteSaveChoicePhoto(position)
            viewModel.minusPhotoIndex()

        }
    }

    override fun getItemCount(): Int {
        return photoIndex
    }

    inner class ChoicePhotoRecyclerViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    }
}