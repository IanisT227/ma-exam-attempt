package com.example.exam_practice_1

import com.example.exam_practice_1.viewmodel.DatabaseHelper
import com.example.exam_practice_1.viewmodel.ExamViewModel
import com.example.exam_practice_1.viewmodel.NetworkService
import com.example.exam_practice_1.viewmodel.Repository
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val service = module {
    single<OkHttpClient> { provideHttpClient() }
    single<Moshi> { provideMoshi() }
    single<Retrofit> { provideRetrofit(get(), get()) }
    single<NetworkService> { provideNetworkService(get()) }
}

val viewModel = module {
    viewModel { ExamViewModel(repository = get(), get()) }
}

val repository = module {
    single<Repository> { Repository(get()) }
}

val database = module {
    single<DatabaseHelper> { DatabaseHelper(androidContext()) }
}



private fun provideRetrofit(moshi: Moshi, client: OkHttpClient) = Retrofit.Builder()
    .baseUrl(SERVER_URL)
    .client(client)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

private fun provideMoshi(): Moshi = Moshi.Builder().build()

private fun provideHttpClient(): OkHttpClient {
    val httpClient = OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS)
    return httpClient.build()
}

private fun provideNetworkService(retrofit: Retrofit): NetworkService =
    retrofit.create(NetworkService::class.java)


private const val SERVER_URL = "http://10.0.2.2:2029/"