package com.kamilh.gotcharacters.interactors

import com.kamilh.gotcharacters.data.Character
import com.kamilh.gotcharacters.di.ActivityScope
import com.kamilh.gotcharacters.repository.Resource
import com.kamilh.gotcharacters.repository.characters.CharactersRepository
import javax.inject.Inject

@ActivityScope
class GetCharacterByName @Inject constructor(
    private val charactersRepository: CharactersRepository
) : Interactor<GetCharacterByName.Params, Resource<Character>> {

    override suspend fun invoke(params: Params): Resource<Character> {
        return charactersRepository.getByName(params.name)
    }

    data class Params(
        val name: String
    )
}
