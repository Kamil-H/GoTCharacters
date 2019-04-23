package com.kamilh.gotcharacters.repository.characters

import com.kamilh.gotcharacters.repository.IceAndFireApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharactersCachedRepository @Inject constructor(
    iceAndFireApi: IceAndFireApi
) : CharactersRepository {
}
