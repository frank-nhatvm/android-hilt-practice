package com.frank.practicehilt.di

import com.frank.practicehilt.common.Config
import com.frank.practicehilt.data.apis.PostAPI
import com.frank.practicehilt.data.apis.QuestionAPI
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOKHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()

        builder.interceptors().add(httpLoggingInterceptor)
        return builder.build()
    }


    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }


    @Provides
    @Singleton
    fun provideMoshiConverterFactory(): MoshiConverterFactory {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    @Singleton
    @Named("StackOverFlowSite")
    fun provideRetrofitStackOverFlow(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder().addConverterFactory(moshiConverterFactory)
            .baseUrl(Config.StackOverFlowUrl)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    @Named("JsonPlaceHolderSite")
    fun provideRetrofitJsonPlaceHolder(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder().addConverterFactory(moshiConverterFactory)
            .baseUrl(Config.JsonPlaceHolder)
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideQuestionAPI(@Named("StackOverFlowSite") retrofit: Retrofit): QuestionAPI {
        return retrofit.create(QuestionAPI::class.java)
    }

    @Provides
    fun providePostAPI(@Named("JsonPlaceHolderSite") retrofit: Retrofit): PostAPI{
        return retrofit.create(PostAPI::class.java)
    }

}