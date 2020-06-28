package com.demo.grabacondemo.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.demo.grabacondemo.R
import com.demo.grabacondemo.modules.Photo
import com.demo.grabacondemo.ui.adapter.PhotoListAdapter
import com.demo.grabacondemo.utils.Utils
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.progress.*

class HomeFragment : Fragment() {
    var mViewModel: MainViewModel? = null
    val utils = Utils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        if(utils.checkInternetConnection(activity!!)){
            getAlbumPhotos()
        }else{
            utils.showToast(activity,getString(R.string.connection_err))
        }
    }

    private fun getAlbumPhotos() {
        toggleProgressbar(1)
        mViewModel?.getData()?.observe(this, Observer { response ->
            val photoList = response as ArrayList<Photo>
            setData(photoList)
            toggleProgressbar(0)
        })
    }

    private fun setData(photoList: ArrayList<Photo>) {
        rvPhotoList.layoutManager = GridLayoutManager(activity,2)
        rvPhotoList.adapter = PhotoListAdapter(activity as Context,photoList)
    }

    fun toggleProgressbar(visibility: Int) {
        try {
            if (visibility == 1) {
                progress.visibility = View.VISIBLE
            } else {
                progress.visibility = View.GONE
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}