package com.demo.grabacondemo.ui

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.demo.grabacondemo.R
import com.demo.grabacondemo.utils.PreferenceUtils
import com.demo.grabacondemo.utils.Utils
import com.google.firebase.auth.FirebaseAuth
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.fragment_user_profile.*
import java.io.File


class UserProfileFragment : Fragment() {
    var preferenceUtils = PreferenceUtils ()
    val REQUEST_GALLERY_IMAGE = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_profile, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    private fun init() {
        if(!PreferenceUtils().getProfileImage(activity!!).equals("") && !PreferenceUtils().getProfileImage(activity!!)?.isEmpty()!!){
            loadProfile(PreferenceUtils().getProfileImage(activity!!)?:"")
        }
        setData()
        btnChangePhoto.setOnClickListener {
            getPhoto()
        }
        btnLogout.setOnClickListener {
           logoutUser()
        }
    }

    private fun setData() {
        val preferenceUtils = PreferenceUtils()
        tvUserName.text = preferenceUtils.getName(activity as Context)
        tvUserEmail.text = preferenceUtils.getEmail(activity as Context)
        tvUserNumber.text = preferenceUtils.getNumber(activity as Context)
        tvUserAge.text = preferenceUtils.getAge(activity as Context)
        tvUserGender.text = preferenceUtils.getGender(activity as Context)
        tvUserLocation.text = preferenceUtils.getLocation(activity as Context)
        tvUserProfession.text = preferenceUtils.getProfission(activity as Context)
    }

    private fun loadProfile(url: String) {
        Glide.with(this)
            .load(url)
            .placeholder(R.drawable.ic_defaut_photo)
            .into(ivUserProfilePic)
    }
    fun getPhoto(){
        Dexter.withActivity(context as Activity)
            .withPermissions(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (report.areAllPermissionsGranted()) {
                        val pickPhoto = Intent(
                            Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                        )
                        startActivityForResult(pickPhoto, REQUEST_GALLERY_IMAGE)
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest>,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).check()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_GALLERY_IMAGE -> if (resultCode == Activity.RESULT_OK) {
                val imageUri: Uri? = data?.data
                loadProfile(imageUri.toString())
                PreferenceUtils().setProfileImage(activity!!,imageUri.toString())
            }
        }
    }

    private fun logoutUser() {
        preferenceUtils.clearealldata(activity as Context)
        FirebaseAuth.getInstance().signOut()
        Utils().launchStartActivity(activity!!, LoginActivity::class.java)
    }


    private fun getFilePath(file: File): String {
        var path = ""
        path = file.getPath()
        return path
    }

}