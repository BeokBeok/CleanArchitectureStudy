package com.beok.repobrowse.domain.entity

data class BranchEntity(
    val name: String
)

fun BranchEntity.mapToModel(): String = name