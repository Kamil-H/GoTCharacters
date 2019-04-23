package com.kamilh.gotcharacters.repository.characters

import com.kamilh.gotcharacters.data.Character
import com.kamilh.gotcharacters.repository.Resource

interface CharactersRepository {

    suspend fun get(pageSize: Int, page: Int): Resource<List<Character>>

    suspend fun getByName(name: String): Resource<Character>
}
