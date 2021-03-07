package giphy.android.clone.ui.trending.api

import giphy.android.clone.base.http.Page
import giphy.android.clone.ui.gif.Gif
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GifTrendingService {

    @GET("/v1/gifs/trending")
    fun getGifs(
        @Query("offset") offset: Int = 0
    ): Observable<Page<Gif>>
}