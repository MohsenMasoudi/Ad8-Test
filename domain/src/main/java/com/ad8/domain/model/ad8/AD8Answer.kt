package com.ad8.domain.model.ad8

data class AD8Answer(
    var `data`: Data?,
    var status: Int?
) {
    data class Data(
        var answers: String?,
        var created_at: String?,
        var id: Int?,
        var updated_at: String?,
        var user_id: Int?,
        var status: String?

    )
}