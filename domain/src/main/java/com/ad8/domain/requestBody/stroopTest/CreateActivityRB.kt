package com.ad8.domain.requestBody.stroopTest


import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class CreateActivityRB(
    var record_id: String?="",
    val action: String?,
    val result: String?,
    val meta: String?="",

):Parcelable