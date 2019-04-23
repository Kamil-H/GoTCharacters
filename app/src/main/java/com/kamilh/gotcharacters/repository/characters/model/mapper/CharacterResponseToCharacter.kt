package com.kamilh.gotcharacters.repository.characters.model.mapper

import com.kamilh.gotcharacters.data.Character
import com.kamilh.gotcharacters.repository.Mapper
import com.kamilh.gotcharacters.repository.characters.model.CharacterResponse
import javax.inject.Inject

class CharacterResponseToCharacter @Inject constructor() : Mapper<CharacterResponse, Character> {
    override fun map(from: CharacterResponse) = Character(
        url = from.url,
        name = from.name,
        gender = from.gender,
        culture = from.culture,
        born = from.born,
        died = from.died,
        titles = from.titles,
        aliases = from.aliases,
        father = from.father,
        mother = from.mother,
        spouse = from.spouse,
        allegiances = from.allegiances,
        books = from.books,
        povBooks = from.povBooks,
        tvSeries = from.tvSeries,
        playedBy = from.playedBy
    )
}
