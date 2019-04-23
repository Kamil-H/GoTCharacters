package com.kamilh.gotcharacters.interactors

import com.kamilh.gotcharacters.di.ActivityScope
import com.kamilh.gotcharacters.repository.characters.CharactersRepository
import javax.inject.Inject

@ActivityScope
class GetCharacters @Inject constructor(
    private val charactersRepository: CharactersRepository
) : Interactor<GetCharacters.Params, Unit> {

    override suspend fun invoke(executeParams: Params) {
        return
    }

    data class Params(
        val page: Int
    )
}
