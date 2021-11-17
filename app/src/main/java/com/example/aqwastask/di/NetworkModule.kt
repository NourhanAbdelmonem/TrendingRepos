package com.example.aqwastask.di

import android.content.Context
import android.util.Log
import com.example.aqwastask.framework.utils.Constants
import com.example.aqwastask.framework.utils.Constants.Companion.CONTENT_TYPE
import com.example.aqwastask.framework.utils.Constants.Companion.CONTENT_TYPE_VALUE
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    private val baseUrl: String
        get() = Constants.BASE_URL

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()


    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext appContext: Context,
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
        val cacheSize = (10 * 1024 * 1024).toLong()
        val myCache = Cache(appContext.cacheDir, cacheSize)
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClientBuilder.addInterceptor(httpLoggingInterceptor)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(20, TimeUnit.SECONDS)
            .cache(myCache)
        return httpClientBuilder.build()
    }


    @Provides
    @Singleton
    fun provideGson(): Gson =
        GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor.Logger { message -> Log.d("LoggingMessage", message) }
        val loggingInterceptor = HttpLoggingInterceptor(logger)
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    @Singleton
    fun provideHeaderInterceptor() =
        Interceptor { chain ->
            var request = chain.request()
            val requestBuilder = request.newBuilder().addHeader(CONTENT_TYPE, CONTENT_TYPE_VALUE)
            request = requestBuilder.build()
            chain.proceed(request)
        }
}
