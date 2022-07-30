package com.juarez.marvelheroes.di

import com.juarez.marvelheroes.api.MarvelApi
import com.juarez.marvelheroes.common.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesHttpInterceptor(): Interceptor {
        return Interceptor {
            val originalUrl = it.request().url

            val url = originalUrl.newBuilder()
                .addQueryParameter("ts", "1")
                .addQueryParameter("apikey", "eaf2572b49ec3b56f6e2f6e756bf49a8")
                .addQueryParameter("hash", "96f6dab3467a036166ca165d2405f651")
                .build()

            val request = it.request()
                .newBuilder()
                .url(url)
                .build()

            it.proceed(request)
        }
    }

    @Singleton
    @Provides
    fun provideHttpClient(httpInterceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(httpInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    @Singleton
    @Provides
    fun provideMarvelApi(retrofit: Retrofit): MarvelApi {
        return retrofit.create(MarvelApi::class.java)
    }
}