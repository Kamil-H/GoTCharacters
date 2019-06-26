package com.kamilh.gotcharacters.interactors

import com.kamilh.gotcharacters.data.Character
import com.kamilh.gotcharacters.repository.Resource
import com.kamilh.gotcharacters.repository.characters.CharactersRepository
import javax.inject.Inject

class GetCharacterByName @Inject constructor(private val charactersRepository: CharactersRepository) {

    suspend operator fun invoke(params: Params): Resource<Character> {
        return charactersRepository.getByName(params.name)
    }

    data class Params(
        val name: String
    )
}
