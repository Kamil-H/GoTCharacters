package com.kamilh.gotcharacters.di

import android.content.Context
import com.kamilh.gotcharacters.GoTCharacters
import com.kamilh.gotcharacters.repository.books.BooksRemoteRepository
import com.kamilh.gotcharacters.repository.books.BooksRepository
import com.kamilh.gotcharacters.repository.characters.CharactersCachedRepository
import com.kamilh.gotcharacters.repository.characters.CharactersRepository
import com.kamilh.gotcharacters.util.AppDispatchers
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module(includes = [RemoteModule::class])
object AppModule {

    @Provides
    @JvmStatic
    fun provideContext(goTCharacters: GoTCharacters): Context = goTCharacters

    @Provides
    @Singleton
    @JvmStatic
    fun provideCoroutineDispatchers() = AppDispatchers(
        io = Dispatchers.IO,
        computation = Dispatchers.Default,
        main = Dispatchers.Main
    )

    @Provides
    @JvmStatic
    fun bindCharactersRepository(charactersCachedRepository: CharactersCachedRepository): CharactersRepository =
        charactersCachedRepository

    @Provides
    @JvmStatic
    fun bindBooksRepository(booksRemoteRepository: BooksRemoteRepository): BooksRepository =
        booksRemoteRepository
}
