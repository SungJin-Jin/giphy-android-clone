package giphy.android.clone.base.http

import giphy.android.clone.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url()
            .newBuilder()
            .addQueryParameter(NAME_API_KEY, BuildConfig.API_KEY)
            .build()
        return chain.proceed(request.newBuilder().url(url).build())
    }

    companion object {
        private const val NAME_API_KEY = "api_key"
    }
}