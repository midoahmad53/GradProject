package com.example.gradproject.base

import android.Manifest
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat

open class BaseActivity : AppCompatActivity() {

    lateinit var activity: AppCompatActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = this
    }

    fun showMessage(
        title: String?,
        message: String?,
        posActionName: String?,
        posAction: DialogInterface.OnClickListener?,
        negativeActionName: String?,
        negativeAction: DialogInterface.OnClickListener?,
        isCancellable: Boolean
    ) {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle(title)
        dialogBuilder.setMessage(message)
        dialogBuilder.setPositiveButton(posActionName, posAction)
        dialogBuilder.setNegativeButton(negativeActionName, negativeAction)
        dialogBuilder.setCancelable(isCancellable)
        dialogBuilder.show()
    }

    fun showMessage(
        title: Int?,
        message: Int,
        posActionName: Int?,
        posAction: DialogInterface.OnClickListener?,
        negativeActionName: Int?,
        negativeAction: DialogInterface.OnClickListener?,
        isCancellable: Boolean
    ) {
        val dialogBuilder = AlertDialog.Builder(this)
        if (title != null)
            dialogBuilder.setTitle(title)
        dialogBuilder.setMessage(message)

        if (posActionName != null)
            dialogBuilder.setPositiveButton(posActionName, posAction)
        if (negativeActionName != null)
            dialogBuilder.setNegativeButton(negativeActionName, negativeAction)
        dialogBuilder.setCancelable(isCancellable)
        dialogBuilder.show()
    }

    var progressDialog: ProgressDialog? = null
    fun showLoadingDialog(loadingMessage: String?): ProgressDialog? {
        progressDialog = ProgressDialog(this)
        progressDialog?.setMessage(loadingMessage)
        progressDialog?.setCancelable(false)
        progressDialog?.show()
        return progressDialog
    }

    fun hideLoadingDialog() {
        progressDialog?.dismiss()
    }

    fun isPermissionGranted(permission: String): Boolean {
        return (ContextCompat.checkSelfPermission(this, permission)
                == PackageManager.PERMISSION_GRANTED)
    }

    fun requestPermissionIfAvailable() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {


        } else {
            requestLocationPermission()
        }
    }

    val Access_Location_Code: Int = 800
    fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA),
            Access_Location_Code
        )
    }


    fun ButtonEffect(button: View) {
        button.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    v.background.setColorFilter(
                        BlendModeColorFilterCompat.createBlendModeColorFilterCompat
                            (-0x1f0b8adf, BlendModeCompat.SRC_ATOP)
                    )
                    v.invalidate()
                }
                MotionEvent.ACTION_UP -> {
                    v.background.clearColorFilter()
                    v.invalidate()
                }
            }
            false
        }
    }
}