package com.beok.reposearch.domain.entity

data class LicenseEntity(
    val name: String
)

fun LicenseEntity.mapToModel(): String = name
