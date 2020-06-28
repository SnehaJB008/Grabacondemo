package com.demo.grabacondemo.ui

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.demo.grabacondemo.R
import com.demo.grabacondemo.modules.Photo
import com.demo.grabacondemo.utils.Utils
import kotlinx.android.synthetic.main.fragment_item_detail.*

class ItemDetailFragment : Fragment() {
    var viewModel: MainViewModel? = null
    val utils = Utils()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =  ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(utils.checkInternetConnection(activity!!)){
            getPhotoDetails()
        }else{
            utils.showToast(activity,getString(R.string.connection_err))
        }
    }

    private fun getPhotoDetails() {
        viewModel?.getPhotoDetail(arguments?.getInt("id",0)?:0)?.observe(this, Observer { response ->
            val photoDetail = response as Photo
            tvTitle.text= photoDetail.title

            try {
                Glide.with(context as Activity)
                    .asBitmap()
                    .load(photoDetail.url)
                    .placeholder(R.drawable.posterimg)
                    .into(ivItem)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
    }

}