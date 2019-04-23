package com.kamilh.gotcharacters.repository.characters

import com.kamilh.gotcharacters.data.Character
import com.kamilh.gotcharacters.repository.IceAndFireApi
import com.kamilh.gotcharacters.repository.Resource
import com.kamilh.gotcharacters.repository.RetrofitRunner
import com.kamilh.gotcharacters.repository.characters.model.mapper.CharacterResponseToCharacter
import com.kamilh.gotcharacters.repository.toListMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharactersCachedRepository @Inject constructor(
    private val iceAndFireApi: IceAndFireApi,
    private val retrofitRunner: RetrofitRunner,
    private val characterResponseToCharacter: CharacterResponseToCharacter
) : CharactersRepository {

    override suspend fun get(pageSize: Int, page: Int): Resource<List<Character>> {
        return retrofitRunner.executeForResponse(
            characterResponseToCharacter.toListMapper(),
            iceAndFireApi.getCharactersAsync(
                page = page,
                pageSize = pageSize
            )
        )
    }

    override suspend fun getByName(name: String): Resource<Character> {
        return when (val resource = retrofitRunner.executeForResponse(characterResponseToCharacter.toListMapper(), iceAndFireApi.filterByNameAsync(name))) {
            is Resource.Data -> Resource.Data(resource.result.first())
            is Resource.Error -> resource
        }
    }
}
