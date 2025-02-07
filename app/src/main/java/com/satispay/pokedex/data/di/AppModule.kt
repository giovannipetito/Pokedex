package com.satispay.pokedex.data.di

import android.app.Application
import com.satispay.pokedex.BuildConfig
import com.satispay.pokedex.data.ApiService
import com.satispay.pokedex.utils.Config
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val cacheInterceptor = Interceptor { chain ->
        val response: Response = chain.proceed(chain.request())
        val cacheControl = CacheControl.Builder()
            .maxAge(30, TimeUnit.DAYS)
            .build()
        response.newBuilder()
            .header("Cache-Control", cacheControl.toString())
            .build()
    }

    @Provides
    @Singleton
    fun provideCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        val cacheDirectory = File(application.applicationContext.cacheDir, "cache")
        return Cache(cacheDirectory, cacheSize.toLong())
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addNetworkInterceptor(cacheInterceptor)
            .cache(cache)
            // .connectTimeout(30, TimeUnit.SECONDS) // connection timeout
            // .readTimeout(30, TimeUnit.SECONDS)    // socket timeout
            // .writeTimeout(30, TimeUnit.SECONDS)   // write timeout
            .addInterceptor { chain: Interceptor.Chain ->
                val newRequest = chain.request().newBuilder()
                    // .addHeader("x-rapidapi-key", BuildConfig.API_KEY)
                    // .addHeader("x-rapidapi-host", BuildConfig.BASE_URL)
                    // .header("User-Agent", Utils.getDeviceName()")
                    .addHeader("applicationId", BuildConfig.APPLICATION_ID)
                    .addHeader("app_version", BuildConfig.VERSION_NAME)
                    .addHeader("os_version", android.os.Build.VERSION.RELEASE)
                    .build()
                chain.proceed(newRequest)
            }
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(loggingInterceptor)
                }
            }

        return builder.build()
    }

    @Provides
    @Singleton
    @Named("baseUrl")
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Config.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @Named("baseUrl")
    fun provideApiService(@Named("baseUrl") retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}