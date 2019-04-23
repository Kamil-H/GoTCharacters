package com.kamilh.gotcharacters.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.kamilh.gotcharacters.BuildConfig
import com.kamilh.gotcharacters.repository.IceAndFireApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object RemoteModule {

    @Provides @Singleton @JvmStatic
    fun provideApi(okHttpClient: OkHttpClient, gson: Gson): IceAndFireApi = retrofitCreator(
            "https://www.anapioficeandfire.com/api/",
            okHttpClient,
            gson
    ).create(IceAndFireApi::class.java)

    @Provides @Singleton @JvmStatic
    fun provideGson(): Gson {
        return GsonBuilder()
            .setPrettyPrinting()
            .create()
    }

    @Provides @Singleton @JvmStatic
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides @Singleton @JvmStatic
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }
    }

    private fun retrofitCreator(url: String, okHttpClient: OkHttpClient, gson: Gson) = Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
}
