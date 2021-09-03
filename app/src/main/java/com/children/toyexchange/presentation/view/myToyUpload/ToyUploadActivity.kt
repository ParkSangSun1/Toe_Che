package com.children.toyexchange.presentation.view.myToyUpload

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.children.toyexchange.R
import com.children.toyexchange.databinding.ActivityToyUploadBinding
import com.children.toyexchange.presentation.base.BaseActivity
import com.children.toyexchange.presentation.widget.extension.showHorizontal
import com.github.dhaval2404.imagepicker.ImagePicker

class ToyUploadActivity : BaseActivity() {
    val binding by binding<ActivityToyUploadBinding>(R.layout.activity_toy_upload)
    companion object{
        lateinit var toyUploadViewModel: ToyUploadViewModel
         var photoIndex : Int = 0
    }
    private var choicePhotoUri: Uri? = null


    override fun onStart() {
        super.onStart()

        toyUploadViewModel.photoIndex.observe(this, Observer {
            //사진 삭제 버튼을 누를시 사진 삭제하는 방법
            binding.choicePhotoRecyclerView.showHorizontal(this)
            binding.choicePhotoRecyclerView.adapter = ChoicePhotoRecyclerAdapter()

            photoIndex = it
            binding.photoIndex.text = "$it/ 5"
        })

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.activity = this

        binding.choicePhotoRecyclerView.showHorizontal(this)
        binding.choicePhotoRecyclerView.adapter = ChoicePhotoRecyclerAdapter()

        toyUploadViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(ToyUploadViewModel::class.java)



    }

    override fun onStop() {
        super.onStop()
        //화면이 멈출때 자동으로 값 저장
        toyUploadViewModel.setPostTitle(binding.postTitle.text.toString())
        toyUploadViewModel.setPostContents(binding.postContents.text.toString())

    }

    override fun onRestart() {
        super.onRestart()
        binding.choicePhotoRecyclerView.showHorizontal(this)
        binding.choicePhotoRecyclerView.adapter = ChoicePhotoRecyclerAdapter()


        //화면이 다시 시작할때 자동저장된 값 불러오기
        binding.postTitle.setText(toyUploadViewModel.postTitle.value.toString())
        binding.postContents.setText(toyUploadViewModel.postContents.value.toString())

    }

    fun backBtnClick(view: View){
        finish()

    }

    fun uploadBtnClick(view: View){
        finish()

    }

    //이미지 선택 클릭
    fun clickChoseProfileImage(view: View) {
        if (photoIndex >= 5){
            Toast.makeText(this,"사진을 더 이상 추가 할수 없습니다",Toast.LENGTH_SHORT).show()
        }else{
            //이미지를 선택
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "이미지를 선택하세요."), 0)
        }

    }



    //이미지 선택후 정보저장후 선택한 이미지 보여주기
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (resultCode) {
            Activity.RESULT_OK -> {
                Log.d("로그","사진 추가 성공 로그")
                //선택된 사진 Uri
                choicePhotoUri = data?.data!!

                //인덱스 값 플러스
                toyUploadViewModel.plusPhotoIndex()

                //선택한 사진 list에 저장
                toyUploadViewModel.setSaveChoicePhoto(choicePhotoUri!!)


            }
            ImagePicker.RESULT_ERROR -> {
                choicePhotoUri = null
                Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT)
                    .show()
            }
            else -> {
                choicePhotoUri = null
                Toast.makeText(this, "작업 취소됨", Toast.LENGTH_SHORT).show()
            }
        }
    }
}