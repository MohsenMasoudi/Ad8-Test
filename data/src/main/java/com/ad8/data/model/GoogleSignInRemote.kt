package com.ad8.data.model

import com.ad8.data.base.ResponseObject
import com.ad8.domain.model.GoogleSignIn
import com.ad8.domain.model.stroopTest.Register

data class GoogleSignInRemote(
    var `data`: Data?,
    var status: Int?
) : ResponseObject<GoogleSignIn> {
    data class Data(
        var token: String?,
        var message: String?
    ): ResponseObject<GoogleSignIn.Data> {
        override fun toDomain(): GoogleSignIn.Data {
            return GoogleSignIn.Data(token,message)
        }
    }

    override fun toDomain(): GoogleSignIn {
        return GoogleSignIn(data?.toDomain(),status)
    }
}