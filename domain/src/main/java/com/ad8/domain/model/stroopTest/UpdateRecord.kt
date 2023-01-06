package com.ad8.domain.model.stroopTest

data class UpdateRecord(
    var `data`: Data?,
    var status: Int?
) {
    data class Data(
        var accuracy: Int?,
        var created_at: String?,
        var id: Int?,
        var speed: Int?,
        var updated_at: String?,
        var user_id: Int?
    )
}