package com.ad8.data.model.stroopTest

import com.ad8.data.base.ResponseObject
import com.ad8.domain.model.stroopTest.Register

data class RegisterRemote(
    var `data`: Data?,
    var status: Int?
) : ResponseObject<Register> {
    data class Data(
        var token: String?
    ): ResponseObject<Register.Data> {
        override fun toDomain(): Register.Data {
            return Register.Data(token)
        }
    }

    override fun toDomain(): Register {
        return Register(data?.toDomain(),status)
    }
}