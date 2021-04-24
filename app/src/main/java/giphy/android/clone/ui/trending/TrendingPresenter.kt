package giphy.android.clone.ui.trending

import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import giphy.android.clone.base.http.PageOptions.PAGE_SIZE
import giphy.android.clone.base.preseneter.BasePresenter
import giphy.android.clone.base.preseneter.RxPresenter
import giphy.android.clone.database.gif.LocalGif
import giphy.android.clone.database.gif.LocalGifRepository
import giphy.android.clone.extensions.addTo
import giphy.android.clone.extensions.onMain
import giphy.android.clone.ui.gif.Gif
import giphy.android.clone.ui.trending.api.TrendingDataSource
import giphy.android.clone.ui.trending.api.TrendingService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject

class TrendingPresenter(
    private val view: TrendingView
) : RxPresenter(), BasePresenter, KoinComponent {

    private val trendingService: TrendingService by inject()
    private val localGifRepository: LocalGifRepository by inject()

    override fun onDestroy() = dispose()

    fun loadGifs() {
        RxPagedListBuilder(dataSource(), getPagedListConfig())
            .buildObservable()
            .subscribe({ view.onLoadPagedGifs(it) }, {})
            .addTo(disposables)
    }

    fun clickLikeGif(gif: Gif) {
        localGifRepository.isOriginalIdExist(gif.id)
            .onMain()
            .subscribe({ exists ->
                when (exists) {
                    true -> deleteExistLike(gif)
                    false -> saveLike(gif)
                }
            }, {})
            .addTo(disposables)
    }

    private fun saveLike(gif: Gif) {
        localGifRepository.insert(LocalGif.mapFor(gif))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ view.onSuccessSaveFavorite() }, {})
            .addTo(disposables)
    }

    private fun deleteExistLike(gif: Gif) {
        localGifRepository.deleteByOriginalId(gif.id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ view.onSuccessDeleteFavorite() }, {})
            .addTo(disposables)
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
