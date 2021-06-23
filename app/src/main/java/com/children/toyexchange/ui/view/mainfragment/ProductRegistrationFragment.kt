package com.children.toyexchange.ui.view.mainfragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.children.toyexchange.R
import com.children.toyexchange.ui.view.myToyUpload.ToyUploadActivity

class ProductRegistrationFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(requireContext(), ToyUploadActivity::class.java)

        startActivity(intent)
        requireActivity().overridePendingTransition(0, 0)

    }

    override fun onStart() {
        super.onStart()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_registration, container, false)
    }


}