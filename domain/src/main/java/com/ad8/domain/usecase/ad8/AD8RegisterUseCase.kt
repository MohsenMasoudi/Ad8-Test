package com.ad8.domain.usecase.ad8


import com.ad8.domain.model.stroopTest.Register
import com.ad8.domain.repository.*
import javax.inject.Inject
import javax.inject.Singleton
import com.ad8.domain.util.Result


@Singleton
class AD8RegisterUseCase @Inject constructor(private val repository: AD8Repository) {
    suspend operator fun invoke(): Result<Register> =
        repository.register()
}