package giphy.android.clone.ui.trending.api

import androidx.paging.PageKeyedDataSource
import giphy.android.clone.ui.gif.Gif
import io.reactivex.disposables.CompositeDisposable

class GifTrendingDataSource(
    private val disposables: CompositeDisposable,
    private val gifTrendingService: GifTrendingService
) : PageKeyedDataSource<Int, Gif>() {

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Gif>) {
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Gif>
    ) {
        disposables.add(
            gifTrendingService
                .getGifs(1)
                .subscribe({ response -> callback.onResult(response.data, null, 2) }, {})
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Gif>) {
        disposables.add(
            gifTrendingService
                .getGifs(params.key)
                .subscribe({ response -> callback.onResult(response.data, params.key + 1) }, {})
        )
    }
}