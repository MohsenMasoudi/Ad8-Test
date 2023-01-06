package com.ad8.domain.requestBody.stroopTest


import com.google.gson.annotations.SerializedName

data class Ad8AnswerRB(
    @SerializedName("answers")
//    @Expose(serialize = false, deserialize = false)
    var answers: String? = null
)