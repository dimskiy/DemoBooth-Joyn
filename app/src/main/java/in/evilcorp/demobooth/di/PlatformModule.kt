package `in`.evilcorp.demobooth.di

import `in`.evilcorp.demobooth.R
import `in`.evilcorp.demobooth.platform.ImageDownloaderImpl
import `in`.evilcorp.demobooth.platform.apiservice.PixabayService
import `in`.evilcorp.demobooth.platform.apiservice.PixabayService.Companion.API_KEY_LABEL
import `in`.evilcorp.demobooth.ui.ImageDownloader
import android.content.Context
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class PlatformModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.HEADERS
            }
        )
        .build()

    @Provides
    @Singleton
    fun provideImageDownloader(okhttpClient: OkHttpClient, context: Context): ImageDownloader {
        val picasso = Picasso.Builder(context)
            .downloader(OkHttp3Downloader(okhttpClient))
            .loggingEnabled(true)
            .build()

        return ImageDownloaderImpl(picasso)
    }

    @Provides
    @Singleton
    @Named(API_KEY_LABEL)
    fun providePixabayApiKey(context: Context) = context.getString(R.string.pixabay_api_key)

    @Provides
    @Singleton
    fun providePixabayService(context: Context, okhttpClient: OkHttpClient): PixabayService =
        Retrofit.Builder()
            .client(okhttpClient)
            .baseUrl(context.getString(R.string.pixabay_base_url))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(PixabayService::class.java)
}