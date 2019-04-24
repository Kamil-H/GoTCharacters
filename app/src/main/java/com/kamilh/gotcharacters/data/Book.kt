package com.kamilh.gotcharacters.data

import org.threeten.bp.LocalDate

data class Book(
    val id: Int,
    val name: String,
    val released: LocalDate?
)
