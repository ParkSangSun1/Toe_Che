package com.children.toyexchange.presentation.view.myToyUpload.fragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.children.toyexchange.R
import com.children.toyexchange.data.models.Post
import com.children.toyexchange.databinding.FragmentMainToyUploadBinding
import com.children.toyexchange.presentation.base.BaseFragment
import com.children.toyexchange.presentation.view.myToyUpload.adapter.ChoicePhotoRecyclerAdapter
import com.children.toyexchange.presentation.view.myToyUpload.ToyUploadViewModel
import com.children.toyexchange.presentation.widget.extension.hide
import com.children.toyexchange.presentation.widget.extension.show
import com.children.toyexchange.presentation.widget.extension.showHorizontal
import com.children.toyexchange.presentation.widget.extension.showShotSnackbar
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MainToyUploadFragment :
    BaseFragment<FragmentMainToyUploadBinding>(R.layout.fragment_main_toy_upload) {
    private val toyUploadViewModel by activityViewModels<ToyUploadViewModel>()
    private var choicePhotoUri: Uri? = null
    private val auth = FirebaseAuth.getInstance()
    private var time: Date? = null

    companion object {
        var photoIndex: Int = 0
    }


    override fun init() {
        binding.fragment = this
        initChoicePhotoRecyclerView()
        observeViewModel()
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

    fun settingAddressBtnClick(view: View) {
        toyUploadViewModel.setPostAddress(null)
        view.findNavController()
            .navigate(R.id.action_mainToyUploadFragment_to_settingAddressFragment)
    }

    fun categoryChoiceBtnClick(view: View) {
        view.findNavController()
            .navigate(R.id.action_mainToyUploadFragment_to_categoryChoiceFragment)
    }

    private fun initChoicePhotoRecyclerView() {
        binding.choicePhotoRecyclerView.showHorizontal(requireContext())
        binding.choicePhotoRecyclerView.adapter = ChoicePhotoRecyclerAdapter(toyUploadViewModel)
    }

    override fun onStop() {
        super.onStop()
        //화면이 멈출때 자동으로 값 저장
        toyUploadViewModel.setPostTitle(binding.postTitle.text.toString())
        toyUploadViewModel.setPostContents(binding.postContents.text.toString())

    }

    private fun observeViewModel() {
        toyUploadViewModel.userChoiceCategory.observe(requireActivity(), Observer {
            binding.userChoiceCategory.text = it
        })

        toyUploadViewModel.postAddress.observe(requireActivity(), Observer {
            binding.settingAddress.text = it
        })

        toyUploadViewModel.toyUploadEvent.observe(this) {
            when (it) {
                1 -> {
                    requireView().showShotSnackbar("업로드에 성공했습니다")
                    toyUploadViewModel.setSuccessPostUpload(true)
                }
                2 -> requireView().showShotSnackbar("업로드에 실패했습니다")
                3 -> requireView().showShotSnackbar("사진을 선택해 주세요")
            }
            binding.loadingBar.hide()
            binding.uploadBtn.isEnabled = true

        }
    }

    fun backBtnClick(view: View) {
        toyUploadViewModel.setBackBtnEvent()
    }

    fun uploadBtnClick(view: View) {
        //Log.d(null)
        if (TextUtils.isEmpty(binding.postTitle.text))
            requireView().showShotSnackbar("제목을 입력해 주세요")
        else {
            if (TextUtils.isEmpty(binding.postContents.text))
                requireView().showShotSnackbar("설명을 입력해 주세요")
            else {
                if (toyUploadViewModel.userChoiceCategory.value == null)
                    requireView().showShotSnackbar("카테고리를 선택해 주세요")
                else {
                    if (toyUploadViewModel.postAddress.value == null)
                        requireView().showShotSnackbar("위치를 설정해 주세요")
                    else {
                        if (toyUploadViewModel.photoIndex.value?.toInt()!! <= 0)
                            requireView().showShotSnackbar("사진을 선택해 주세요")
                        else {
                            requireView().showShotSnackbar("약간의 시간이 걸릴 수 있습니다")
                            binding.uploadBtn.isEnabled = false
                            binding.loadingBar.show()
                            saveFirebase()
                        }
                    }
                }
            }
        }
    }

    //현재 시간
    //  private fun nowDate() = SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분").format(Date(System.currentTimeMillis()))
    private fun nowDateSave() {
        time = Date(System.currentTimeMillis())
    }


    private fun saveFirebase() {
        nowDateSave()
        val toyUploadDataClassSaveDate = SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분").format(time)
        val toyUploadSaveDate = SimpleDateFormat("yyyyMMddhhmmss").format(time)
        val toyUploadDataClass = Post(
            binding.postTitle.text.toString(),
            binding.postContents.text.toString(),
            toyUploadViewModel.userChoiceCategory.value.toString(),
            toyUploadViewModel.postAddress.value.toString(),
            toyUploadViewModel.photoIndex.value.toString(),
            toyUploadDataClassSaveDate,
            auth.uid.toString(),
            toyUploadSaveDate,
            auth.uid.toString() + binding.postTitle.text.toString() + toyUploadSaveDate
        )
        toyUploadViewModel.toyUpload(
            toyUploadDataClass,
            auth.uid.toString() + binding.postTitle.text.toString() + toyUploadSaveDate
        )
    }

    //이미지 선택 클릭
    fun clickChoseProfileImage(view: View) {
        if (photoIndex >= 5) {
            Toast.makeText(requireContext(), "사진을 더 이상 추가 할수 없습니다", Toast.LENGTH_SHORT).show()
        } else {
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
                Log.d("로그", "사진 추가 성공 로그")
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