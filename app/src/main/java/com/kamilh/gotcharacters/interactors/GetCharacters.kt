package com.kamilh.gotcharacters.interactors

import com.kamilh.gotcharacters.data.Character
import com.kamilh.gotcharacters.data.PaginationRequest
import com.kamilh.gotcharacters.data.PaginationResponse
import com.kamilh.gotcharacters.repository.Resource
import com.kamilh.gotcharacters.repository.characters.CharactersRepository
import com.kamilh.gotcharacters.repository.then
import javax.inject.Inject

class GetCharacters @Inject constructor(private val charactersRepository: CharactersRepository) {

    suspend operator fun invoke(params: PaginationRequest<Character>): Resource<PaginationResponse<Character>> {
        return charactersRepository.get(params.pageSize, params.page) then {
            Resource.Data(
                PaginationResponse(
                    responseList = it,
                    request = params
                )
            )
        }
    }
}
