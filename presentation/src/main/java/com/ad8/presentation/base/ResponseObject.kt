package com.ad8.presentation.base

interface ResponseObject<out DomainObject : Any?> {
    fun toDomain(): DomainObject
}

