package org.digitalcampus.oppia.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import org.digitalcampus.mobile.learning.R

open class AppFragment : Fragment() {

    val TAG = this.javaClass.simpleName

    private var progressDialog: ProgressDialog? = null



    fun toast(message: String?) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    fun toast(stringId: Int) {
        toast(getString(stringId))
    }

    fun showProgressDialog(message: String?, cancelable: Boolean = false, indeterminate: Boolean = true) {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
            progressDialog = null
        }
        progressDialog = ProgressDialog(activity, R.style.Oppia_AlertDialogStyle)
        progressDialog!!.setMessage(message)
        progressDialog!!.setCancelable(cancelable)
        progressDialog!!.setCanceledOnTouchOutside(false)
        if (!indeterminate) {
            progressDialog!!.isIndeterminate = false
            progressDialog!!.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
        }
        progressDialog!!.show()
    }

    fun hideProgressDialog() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
            progressDialog = null
        }
    }

    protected val isProgressDialogShowing: Boolean
        get() = progressDialog != null && progressDialog!!.isShowing

}