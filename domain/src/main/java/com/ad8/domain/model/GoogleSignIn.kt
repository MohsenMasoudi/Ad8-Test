package com.ad8.domain.model



data class GoogleSignIn(
    var `data`: Data?,
    var status: Int?
) {
    data class Data(
        var token: String?,
        var message: String?
    )}