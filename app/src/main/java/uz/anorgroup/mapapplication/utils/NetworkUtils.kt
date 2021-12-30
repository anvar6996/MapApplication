package uz.anorgroup.mapapplication.utils

import android.content.Context
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

fun OkHttpClient.Builder.addLoggingInterceptor(context: Context): OkHttpClient.Builder {
    HttpLoggingInterceptor.Level.HEADERS
    val logging = HttpLoggingInterceptor.Logger { message -> Timber.tag("HTTP").d(message) }
    addInterceptor(ChuckInterceptor(context))
        .addInterceptor(HttpLoggingInterceptor(logging))
    return this
}

//fun addHeaderInterceptor() = Interceptor { chain ->
//    val request = chain.request()
//    val newRequest = request.newBuilder().removeHeader("Authorization").addHeader("Authorization", pref.accessToken).build()
//
//    val response = chain.proceed(newRequest)
//    response
//}

