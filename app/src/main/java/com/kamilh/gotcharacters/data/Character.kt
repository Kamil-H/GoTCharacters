package com.kamilh.gotcharacters.data

data class Character(
    val id: Int,
    val url: String,
    val name: String,
    val gender: String,
    val culture: String,
    val born: String,
    val died: String,
    val father: String,
    val mother: String,
    val books: List<Int>,
    val tvSeries: List<String>,
    val playedBy: List<String>
)
