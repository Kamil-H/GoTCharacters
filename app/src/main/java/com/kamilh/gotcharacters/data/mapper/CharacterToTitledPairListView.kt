package com.kamilh.gotcharacters.data.mapper

import com.kamilh.gotcharacters.R
import com.kamilh.gotcharacters.custom_views.PairTextView
import com.kamilh.gotcharacters.custom_views.TitledPairListView
import com.kamilh.gotcharacters.data.Character
import com.kamilh.gotcharacters.repository.Mapper
import com.kamilh.gotcharacters.util.ResourceProvider
import javax.inject.Inject

class CharacterToGeneralInfoTitledPairListView @Inject constructor(
    private val resourceProvider: ResourceProvider
) : Mapper<Character, TitledPairListView.Configuration> {

    override fun map(from: Character) = TitledPairListView.Configuration(
        title = resourceProvider.getString(R.string.CharacterDetails_general_info_text),
        pairs = listOf(
            PairTextView.Configuration(
                left = resourceProvider.getString(R.string.CharacterDetails_name_text),
                right = textOrDash(from.name)
            ),
            PairTextView.Configuration(
                left = resourceProvider.getString(R.string.CharacterDetails_culture_text),
                right = textOrDash(from.culture)
            ),
            PairTextView.Configuration(
                left = resourceProvider.getString(R.string.CharacterDetails_born_text),
                right = textOrDash(from.born)
            ),
            PairTextView.Configuration(
                left = resourceProvider.getString(R.string.CharacterDetails_died_text),
                right = textOrDash(from.died)
            ),
            PairTextView.Configuration(
                left = resourceProvider.getString(R.string.CharacterDetails_father_text),
                right = textOrDash(from.father)
            ),
            PairTextView.Configuration(
                left = resourceProvider.getString(R.string.CharacterDetails_mother_text),
                right = textOrDash(from.mother)
            )
        )
    )

    private fun textOrDash(text: String) = if (text.isNotEmpty()) text else "-"
}

class CharacterToTvSeriesTitledPairListView @Inject constructor(
    private val resourceProvider: ResourceProvider
) : Mapper<Character, TitledPairListView.Configuration> {

    override fun map(from: Character) = TitledPairListView.Configuration(
        title = resourceProvider.getString(R.string.CharacterDetails_appearances_text),
        pairs = from.tvSeries.map { PairTextView.Configuration(left = it) }
    )
}

class CharacterToPlayedByTitledPairListView @Inject constructor(
    private val resourceProvider: ResourceProvider
) : Mapper<Character, TitledPairListView.Configuration> {

    override fun map(from: Character) = TitledPairListView.Configuration(
        title = resourceProvider.getString(R.string.CharacterDetails_played_by_text),
        pairs = from.playedBy.map { PairTextView.Configuration(left = it) }
    )
}
