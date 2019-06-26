package com.kamilh.gotcharacters.repository.characters

import com.kamilh.gotcharacters.data.Character
import com.kamilh.gotcharacters.repository.*
import com.kamilh.gotcharacters.repository.characters.model.mapper.CharacterResponseToCharacter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharactersCachedRepository @Inject constructor(
    private val iceAndFireApi: IceAndFireApi,
    private val retrofitRunner: RetrofitRunner,
    private val characterResponseToCharacter: CharacterResponseToCharacter
) : CharactersRepository {

    override suspend fun get(pageSize: Int, page: Int): Resource<List<Character>> {
        return retrofitRunner.executeForResponse(characterResponseToCharacter.toListMapper()) {
            iceAndFireApi.getCharacters(page = page, pageSize = pageSize)
        }
    }

    override suspend fun getByName(name: String): Resource<Character> {
        return retrofitRunner.executeForResponse(characterResponseToCharacter.toListMapper()) {
            iceAndFireApi.filterByName(name)
        } then {
            Resource.Data(it.first())
        }
    }
}
