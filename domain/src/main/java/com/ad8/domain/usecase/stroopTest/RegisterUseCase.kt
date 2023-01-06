package com.ad8.domain.usecase.stroopTest


import com.ad8.domain.model.stroopTest.Register
import com.ad8.domain.repository.*
import javax.inject.Inject
import javax.inject.Singleton
import com.ad8.domain.util.Result


@Singleton
class RegisterUseCase @Inject constructor(private val repository: StroopTestRepository) {
    suspend operator fun invoke(): Result<Register> =
        repository.register()
}