package com.shiftkey.codingchallenge.di

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.shiftkey.codingchallenge.data.repository.ShiftRepositoryImpl
import com.shiftkey.codingchallenge.data.source.remote.RetrofitService
import com.shiftkey.codingchallenge.domain.repository.ShiftRepository
import com.shiftkey.codingchallenge.utils.Constants.BASE_URL
import com.shiftkey.codingchallenge.utils.DateUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Suppress("DEPRECATION")
@InstallIn(SingletonComponent::class)
@Module
class MainModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }


    @Singleton
    @Provides
    fun provideRetrofitService(retrofit: Retrofit): RetrofitService {
        return retrofit.create(RetrofitService::class.java)
    }


    @Singleton
    @Provides
    fun provideShiftRepository(
        retrofitService: RetrofitService
    ): ShiftRepository {
        return ShiftRepositoryImpl(retrofitService)
    }


    @Singleton
    @Provides
    fun provideDateUtil(): DateUtil {
        return DateUtil()
    }


}