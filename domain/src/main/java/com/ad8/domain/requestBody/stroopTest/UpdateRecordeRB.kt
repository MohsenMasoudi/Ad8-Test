package com.ad8.domain.requestBody.stroopTest


import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class UpdateRecordeRB(
    var _method: String?="",
    val speed: Int?,
    val accuracy: Int?

):Parcelable