package com.ad8.domain.repository


import com.ad8.domain.model.ad8.AD8Answer
import com.ad8.domain.model.stroopTest.Register
import com.ad8.domain.requestBody.stroopTest.Ad8AnswerRB
import com.ad8.domain.util.Result

interface AD8Repository {
    suspend fun register(): Result<Register>
    suspend fun createAnswer(body: Ad8AnswerRB?): Result<AD8Answer>


}