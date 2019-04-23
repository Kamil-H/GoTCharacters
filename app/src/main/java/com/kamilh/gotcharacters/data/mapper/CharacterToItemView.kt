package com.kamilh.gotcharacters.data.mapper

import com.kamilh.gotcharacters.custom_views.ItemView
import com.kamilh.gotcharacters.data.Character
import com.kamilh.gotcharacters.repository.Mapper
import javax.inject.Inject

class CharacterToItemView @Inject constructor() : Mapper<Character, ItemView.Configuration> {
    override fun map(from: Character) = ItemView.Configuration(
        id = from.id,
        upperText = from.gender,
        middleText = from.name,
        bottomText = bornDiedText(from)
    )

    private fun bornDiedText(character: Character) = if (character.born.isEmpty() && character.died.isEmpty()) {
        "-"
    } else if (character.born.isEmpty() && character.died.isNotEmpty()) {
        character.died
    } else if (character.born.isNotEmpty() && character.died.isEmpty()) {
        character.born
    } else {
        "${character.born} - ${character.died}"
    }
}
