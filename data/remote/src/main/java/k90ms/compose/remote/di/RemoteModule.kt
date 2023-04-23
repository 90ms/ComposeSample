package k90ms.compose.remote.di

import android.app.Application
import android.util.Log
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import k90ms.compose.remote.BuildConfig
import k90ms.compose.remote.COMMON_NETWORK_ERROR_CODE
import k90ms.compose.remote.COMMON_NETWORK_ERROR_MESSAGE
import k90ms.compose.remote.HEADER_KEY_AUTHORIZATION
import k90ms.compose.remote.HEADER_KEY_CONTENT_TYPE
import k90ms.compose.remote.URL
import k90ms.compose.remote.api.OpenAIService
import k90ms.compose.remote.model.OpenAI
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(
        app: Application
    ) = OkHttpClient.Builder()
        .addInterceptor(ChuckerInterceptor(app))
//        .addInterceptor(provideInterceptor())
        .addInterceptor {
            val original = it.request()

            it.proceed(
                original.newBuilder().apply {
                    addHeader(HEADER_KEY_CONTENT_TYPE, "application/json")
                    addHeader(HEADER_KEY_AUTHORIZATION, "Bearer ${BuildConfig.API_KEY}")
                }.build()
            )
        }
        .addNetworkInterceptor(
            HttpLoggingInterceptor {
                Log.i("OkHttp ::", it)
            }.apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
        .build()

    private fun createCommonHeaderBuilder(request: Request): Request.Builder {
        val builder: Request.Builder = request.newBuilder()
        builder.header(HEADER_KEY_CONTENT_TYPE, "application/json")
        builder.header(HEADER_KEY_AUTHORIZATION, "Bearer ${BuildConfig.API_KEY}")
        return builder
    }

    private fun provideInterceptor(): Interceptor {
        return Interceptor {
            try {
                it.proceed(createCommonHeaderBuilder(it.request()).build())
            } catch (e: Exception) {

                Log.e("Error :: ", e.message.toString())
                Response.Builder()
                    .protocol(Protocol.HTTP_1_1)
                    .request(it.request())
                    .message(COMMON_NETWORK_ERROR_MESSAGE)
                    .code(COMMON_NETWORK_ERROR_CODE)
                    .body("".toResponseBody())
                    .build()
            }
        }
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideOpenAI(
        retrofit: Retrofit
    ): OpenAIService = retrofit.create(OpenAIService::class.java)
}