package com.ad8.domain.model.stroopTest

data class Register(
    var `data`: Data?,
    var status: Int?
) {
    data class Data(
        var token: String?
    )
}