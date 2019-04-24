package com.kamilh.gotcharacters.repository.characters.model.mapper

import com.kamilh.gotcharacters.data.Character
import com.kamilh.gotcharacters.extensions.urlToId
import com.kamilh.gotcharacters.repository.Mapper
import com.kamilh.gotcharacters.repository.characters.model.CharacterResponse
import javax.inject.Inject

class CharacterResponseToCharacter @Inject constructor() : Mapper<CharacterResponse, Character> {
    override fun map(from: CharacterResponse) = Character(
        id = urlToId(from.url),
        url = from.url,
        name = from.name,
        gender = from.gender,
        culture = from.culture,
        born = from.born,
        died = from.died,
        father = from.father,
        mother = from.mother,
        books = from.books.map(this::urlToId),
        tvSeries = from.tvSeries,
        playedBy = from.playedBy
    )
}
