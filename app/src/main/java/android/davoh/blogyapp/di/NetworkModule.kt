package android.davoh.blogyapp.di

import android.davoh.api.ApiRest
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    
    @Provides
    fun providesApiService( client : OkHttpClient): ApiRest {
        return Retrofit.Builder()
            .baseUrl("http://192.168.100.6/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client)
            .build()
            .create(ApiRest::class.java)
    }
    
    @Provides
    fun provideUnsafeOkHttpClient(): OkHttpClient {
        return provideUnsafeOkHttpClientTimeOut(25000L)
    }
    
    private fun provideUnsafeOkHttpClientTimeOut(milliseconds: Long): OkHttpClient {
        return try {
            val builder = OkHttpClient.Builder()
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
            builder
                .callTimeout(milliseconds, TimeUnit.MILLISECONDS)
                .connectTimeout(milliseconds, TimeUnit.MILLISECONDS)
                .readTimeout(milliseconds, TimeUnit.MILLISECONDS)
                .writeTimeout(milliseconds, TimeUnit.MILLISECONDS)
                .build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
    
    
}