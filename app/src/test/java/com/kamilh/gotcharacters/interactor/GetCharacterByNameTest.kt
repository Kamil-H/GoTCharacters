package com.kamilh.gotcharacters.interactor

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kamilh.gotcharacters.data.Character
import com.kamilh.gotcharacters.di.ActivityScope
import com.kamilh.gotcharacters.extensions.mock
import com.kamilh.gotcharacters.extensions.whenever
import com.kamilh.gotcharacters.interactors.GetCharacterByName
import com.kamilh.gotcharacters.repository.Resource
import com.kamilh.gotcharacters.repository.characters.CharactersRepository
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

@ActivityScope
class GetCharacterByNameTest {

    @Rule
    @JvmField
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private val charactersRepository = mock<CharactersRepository>()

    private val getCharacterByName by lazy { GetCharacterByName(charactersRepository) }

    @Test
    fun `GetCharacterByName test returns expected value`() = runBlocking {
        val resource = mock<Resource.Data<Character>>()

        whenever(charactersRepository.getByName("Name")).thenReturn(resource)

        val response = getCharacterByName.invoke(GetCharacterByName.Params(name = "Name"))

        assert(response == resource)
    }
}
