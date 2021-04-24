package giphy.android.clone.ui.trending.api

import androidx.paging.PageKeyedDataSource
import giphy.android.clone.base.http.PageOptions.PAGE_SIZE
import giphy.android.clone.extensions.addTo
import giphy.android.clone.ui.gif.Gif
import io.reactivex.disposables.CompositeDisposable

class TrendingDataSource(
    private val disposables: CompositeDisposable,
    private val trendingService: TrendingService
) : PageKeyedDataSource<Int, Gif>() {

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Gif>) = Unit

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Gif>
    ) {
        trendingService
            .getGifs()
            .subscribe({ response -> callback.onResult(response.data, null, 1) }, {})
            .addTo(disposables)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Gif>) {
        trendingService
            .getGifs(params.key * PAGE_SIZE)
            .subscribe({ response -> callback.onResult(response.data, params.key + 1) }, {})
            .addTo(disposables)
    }
}