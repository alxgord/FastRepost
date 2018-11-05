package com.artto.fastrepost.di.data

import com.artto.fastrepost.data.instagram.api.InstagramApi
import com.artto.fastrepost.data.instagram.api.InstagramApiConstants
import com.artto.fastrepost.data.instagram.api.InstagramApiMethods
import com.artto.fastrepost.di.scope.ApplicationScope
import com.artto.fastrepost.data.instagram.InstagramRepository
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

@Module
class InstagramApiModule {

    @Provides
    @ApplicationScope
    fun provideOkHttpClient(): OkHttpClient =
            OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                    .build()

    @Provides
    @ApplicationScope
    fun provideObjectMapper(): ObjectMapper =
            ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    @Provides
    @ApplicationScope
    fun provideConverterFactory(objectMapper: ObjectMapper): JacksonConverterFactory =
            JacksonConverterFactory.create(objectMapper)

    @Provides
    @ApplicationScope
    fun provideRetrofit(client: OkHttpClient, converterFactory: JacksonConverterFactory): Retrofit =
            Retrofit.Builder()
                    .baseUrl(InstagramApiConstants.BASE_URL)
                    .client(client)
                    .addConverterFactory(converterFactory)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

    @Provides
    @ApplicationScope
    fun provideApiMethods(retrofit: Retrofit): InstagramApiMethods =
            retrofit.create<InstagramApiMethods>(InstagramApiMethods::class.java)

    @Provides
    @ApplicationScope
    fun provideInstagramApi(methods: InstagramApiMethods): InstagramApi =
            InstagramApi(methods)

    @Provides
    @ApplicationScope
    fun provideInstagramInteract(api: InstagramApi): InstagramRepository =
            InstagramRepository(api)
}