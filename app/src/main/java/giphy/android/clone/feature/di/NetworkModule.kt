package giphy.android.clone.feature.di

import giphy.android.clone.BuildConfig
import giphy.android.clone.base.http.ApiKeyInterceptor
import giphy.android.clone.ui.trending.api.GifTrendingService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


val networkModule = module {
    factory { provideOkHttpClient() }
    single { provideRetrofit(get()) }

    single { get<Retrofit>().create(GifTrendingService::class.java) }

}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .client(okHttpClient)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(ApiKeyInterceptor())
        .build()
}
