package com.children.toyexchange.presentation.view.myToyUpload.fragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.children.toyexchange.R
import com.children.toyexchange.data.models.ToyUpload
import com.children.toyexchange.databinding.FragmentMainToyUploadBinding
import com.children.toyexchange.presentation.view.myToyUpload.adapter.ChoicePhotoRecyclerAdapter
import com.children.toyexchange.presentation.view.myToyUpload.ToyUploadViewModel
import com.children.toyexchange.presentation.widget.extension.showHorizontal
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainToyUploadFragment : Fragment() {

    private lateinit var binding : FragmentMainToyUploadBinding
    private val toyUploadViewModel by activityViewModels<ToyUploadViewModel>()
    private var choicePhotoUri: Uri? = null
    lateinit var toyUploadDataClass : ToyUpload
    private val auth = FirebaseAuth.getInstance()

    companion object{
        var photoIndex : Int = 0
    }

    override fun onStart() {
        super.onStart()
        toyUploadViewModel.getToyCategory()
        toyUploadViewModel.photoIndex.observe(this, Observer {
            //사진 삭제 버튼을 누를시 사진 삭제하는 방법
            initChoicePhotoRecyclerView()
            toyUploadViewModel.setSearchAddressNull()
            photoIndex = it
            binding.photoIndex.text = "$it/ 5"
        })
    }

    fun settingAddressBtnClick(view: View){
        toyUploadViewModel.setPostAddress(null)
        view.findNavController().navigate(R.id.action_mainToyUploadFragment_to_settingAddressFragment)
    }

    fun categoryChoiceBtnClick(view: View){
        view.findNavController().navigate(R.id.action_mainToyUploadFragment_to_categoryChoiceFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main_toy_upload,container,false)
        binding.fragment = this
        initChoicePhotoRecyclerView()
        observeViewModel()
        return binding.root
    }

    private fun initChoicePhotoRecyclerView(){
        binding.choicePhotoRecyclerView.showHorizontal(requireContext())
        binding.choicePhotoRecyclerView.adapter = ChoicePhotoRecyclerAdapter(toyUploadViewModel)
    }


    override fun onStop() {
        super.onStop()
        //화면이 멈출때 자동으로 값 저장
        toyUploadViewModel.setPostTitle(binding.postTitle.text.toString())
        toyUploadViewModel.setPostContents(binding.postContents.text.toString())

    }

    private fun observeViewModel(){
        toyUploadViewModel.userChoiceCategory.observe(requireActivity(), Observer {
            binding.userChoiceCategory.text = it
        })

        toyUploadViewModel.postAddress.observe(requireActivity(), Observer {
            binding.settingAddress.text = it
        })
    }

    fun backBtnClick(view: View){

    }

    fun uploadBtnClick(view: View){
            toyUploadViewModel.uploadPhotoStorage(auth.uid.toString(), binding.postTitle.text.toString())
            saveFirebaseFireStore()
    }


    private fun saveFirebaseFireStore(){
        toyUploadDataClass= ToyUpload(binding.postTitle.text.toString(),binding.postContents.text.toString(),toyUploadViewModel.userChoiceCategory.value.toString(),toyUploadViewModel.searchAddressResponse.value.toString(),null)
        toyUploadViewModel.toyUpload(auth.uid.toString(),binding.postTitle.text.toString(), toyUploadDataClass)
            .addOnSuccessListener {
                Log.d("로그","요기")
            }
            .addOnFailureListener {
                Log.d("로그","요기2 $it")
            }
    }

    //이미지 선택 클릭
    fun clickChoseProfileImage(view: View) {
        if (photoIndex >= 5){
            Toast.makeText(requireContext(),"사진을 더 이상 추가 할수 없습니다",Toast.LENGTH_SHORT).show()
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
                Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT)
                    .show()
            }
            else -> {
                choicePhotoUri = null
                Toast.makeText(requireContext(), "작업 취소됨", Toast.LENGTH_SHORT).show()
            }
        }
    }
}