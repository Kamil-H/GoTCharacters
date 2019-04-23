package com.kamilh.gotcharacters.interactors

import com.kamilh.gotcharacters.data.Character
import com.kamilh.gotcharacters.data.PaginationRequest
import com.kamilh.gotcharacters.data.PaginationResponse
import com.kamilh.gotcharacters.di.ActivityScope
import com.kamilh.gotcharacters.repository.Resource
import com.kamilh.gotcharacters.repository.characters.CharactersRepository
import javax.inject.Inject

@ActivityScope
class GetCharacters @Inject constructor(
    private val charactersRepository: CharactersRepository
) : Interactor<PaginationRequest<Character>, Resource<PaginationResponse<Character>>> {

    override suspend fun invoke(params: PaginationRequest<Character>): Resource<PaginationResponse<Character>> {
        return when (val response = charactersRepository.get(params.pageSize, params.page)) {
            is Resource.Data -> Resource.Data(
                PaginationResponse(
                    responseList = response.result,
                    request = params
                )
            )
            is Resource.Error -> response
        }
    }
}
