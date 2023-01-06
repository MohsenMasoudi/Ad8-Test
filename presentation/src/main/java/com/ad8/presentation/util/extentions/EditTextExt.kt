package com.ad8.presentation.util.extentions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.fragment.app.FragmentActivity
import java.util.*

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable?.trim().toString())
        }
    })
}
fun EditText.afterDelayTextChanged(afterTextChanged: (String) -> Unit,  activity: FragmentActivity,time:Long=500,) {
    var  timer: Timer?=null
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (timer != null) {
                timer?.cancel()
            }
        }

        override fun afterTextChanged(editable: Editable?) {
            timer= Timer()
            timer?.schedule(object : TimerTask(){
                override fun run() {

                    activity.runOnUiThread {
                        afterTextChanged.invoke(editable?.trim().toString())
                    }
                }

            },time)
        }
    })
}