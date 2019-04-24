package com.kamilh.gotcharacters.extensions

import com.kamilh.gotcharacters.repository.Mapper

fun Mapper<*, *>.urlToId(url: String) = url.split("/").last().toInt()
