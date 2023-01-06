package com.ad8.presentation.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.database.Cursor
import android.graphics.Insets
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.text.TextUtils
import android.util.DisplayMetrics
import android.util.Patterns
import android.view.View
import android.view.WindowInsets
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.FragmentActivity
import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


object GeneralHelper {

    fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    fun hideKeyboard(activity: FragmentActivity) {
        val imm: InputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view: View? = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun getRealPathFromUri(context: Context, contentUri: Uri?): String? {
        var cursor: Cursor? = null
        return try {
            val proj = arrayOf(MediaStore.Images.Media.DATA)
            cursor = contentUri?.let { context.contentResolver.query(it, proj, null, null, null) }
            val columnIndex: Int = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            cursor.getString(columnIndex)
        } finally {
            cursor?.close()
        }
    }




    @SuppressLint("SimpleDateFormat")
    fun covertNumericDate(dataDate: String?): String? {
        if(dataDate==null) return ""
        var convertedTime: String? = null
        try {
            val pasTime = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            pasTime.timeZone = TimeZone.getTimeZone("GMT")
            val formatter = SimpleDateFormat("yyyy.MM.dd")
            val formattedDate = formatter.format(
                pasTime.parse(
                    dataDate.replace(
                        "Z$".toRegex(),
                        "+0000"
                    )
                )
            )
            convertedTime=formattedDate

        } catch (e: ParseException) {
            e.printStackTrace()
            Timber.e(e.message!!)
        }
        return convertedTime
    }

    fun getScreenWidth(activity: Activity): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = activity.windowManager.currentWindowMetrics
            val insets: Insets = windowMetrics.windowInsets
                .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            windowMetrics.bounds.width() - insets.left - insets.right
        } else {
            val displayMetrics = DisplayMetrics()
            activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
            displayMetrics.widthPixels
        }
    }

}