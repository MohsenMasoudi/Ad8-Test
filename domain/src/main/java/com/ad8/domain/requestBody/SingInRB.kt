package com.ad8.domain.requestBody


import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class SingInRB(
    var first_name: String?="",
    val last_name: String?,
    val google_id: String?,
    val email: String?,
    val image: String?,

):Parcelable