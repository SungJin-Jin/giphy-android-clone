package giphy.android.clone.ui.trending

import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import giphy.android.clone.base.http.PageOptions.PAGE_SIZE
import giphy.android.clone.base.preseneter.BasePresenter
import giphy.android.clone.base.preseneter.RxPresenter
import giphy.android.clone.ui.gif.Gif
import giphy.android.clone.ui.trending.api.TrendingDataSource
import giphy.android.clone.ui.trending.api.TrendingService
import org.koin.core.KoinComponent
import org.koin.core.inject

class TrendingPresenter(
    val view: TrendingView
) : RxPresenter(), BasePresenter, KoinComponent {

    private val trendingService: TrendingService by inject()

    override fun onDestroy() = dispose()

    fun loadGifs() {
        add(
            RxPagedListBuilder(dataSource(), getPagedListConfig())
                .buildObservable()
                .subscribe({ view.onLoadPagedGifs(it) }, {})
        )
    }

    private fun dataSource(): DataSource.Factory<Int, Gif> {
        return object : DataSource.Factory<Int, Gif>() {
            override fun create(): DataSource<Int, Gif> =
                TrendingDataSource(disposables, trendingService)
        }
    }

    private fun getPagedListConfig() = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(PAGE_SIZE)
        .build()

}
