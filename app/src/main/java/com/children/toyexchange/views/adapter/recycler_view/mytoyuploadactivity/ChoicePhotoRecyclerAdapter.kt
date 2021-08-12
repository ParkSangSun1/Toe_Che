package com.children.toyexchange.views.mytoyupload.adapter

import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.children.toyexchange.R
import com.children.toyexchange.views.mytoyupload.ToyUploadActivity
import com.children.toyexchange.views.mytoyupload.ToyUploadActivity.Companion.photoIndex
import com.children.toyexchange.views.mytoyupload.ToyUploadActivity.Companion.toyUploadViewModel

class ChoicePhotoRecyclerAdapter : RecyclerView.Adapter<ChoicePhotoRecyclerViewHolder>() {


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
            holder.view.findViewById<ImageView>(R.id.recycler_image).setImageURI(toyUploadViewModel.saveChoicePhoto.value?.get(position))
        }

        //사진 삭제 버튼 클릭시
        holder.view.findViewById<ImageView>(R.id.del_btn).setOnClickListener {
            Log.d("로그","인덱스 값 : $position, viewmodelsavephoto의 인덱스 값 ${toyUploadViewModel.saveChoicePhoto.value?.get(position)}")
            toyUploadViewModel.deleteSaveChoicePhoto(position)
            toyUploadViewModel.minusPhotoIndex()

        }
    }

    override fun getItemCount(): Int {
        return photoIndex
    }
}

class ChoicePhotoRecyclerViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

}