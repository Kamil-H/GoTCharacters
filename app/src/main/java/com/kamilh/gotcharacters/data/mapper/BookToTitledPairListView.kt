package com.kamilh.gotcharacters.data.mapper

import com.kamilh.gotcharacters.custom_views.PairTextView
import com.kamilh.gotcharacters.custom_views.TitledPairListView
import com.kamilh.gotcharacters.data.Book
import com.kamilh.gotcharacters.repository.Mapper
import javax.inject.Inject

class BookToTitledPairListView @Inject constructor() : Mapper<List<Book>, TitledPairListView.Configuration> {

    override fun map(from: List<Book>) = TitledPairListView.Configuration(
        title = "Character in books",
        pairs = from.map { PairTextView.Configuration(left = it.name, right = textOrDash(it.released?.year?.toString())) }
    )

    private fun textOrDash(text: String?) = if (text != null && text.isNotEmpty()) text else "-"
}
