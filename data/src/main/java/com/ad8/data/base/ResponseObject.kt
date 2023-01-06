package com.ad8.data.base

interface ResponseObject<out DomainObject : Any?> {
    fun toDomain(): DomainObject
}

