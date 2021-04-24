package giphy.android.clone.ui.favorite

import giphy.android.clone.base.preseneter.BasePresenter
import giphy.android.clone.base.preseneter.RxPresenter
import giphy.android.clone.database.gif.LocalGif
import giphy.android.clone.database.gif.LocalGifRepository
import giphy.android.clone.extensions.addTo
import giphy.android.clone.extensions.onMain
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject

class FavoritePresenter(
        private val view: FavoriteView
) : RxPresenter(), BasePresenter, KoinComponent {

    private val localGifRepository: LocalGifRepository by inject()

    override fun onDestroy() = dispose()

    fun loadGifs() {
        localGifRepository.getAll()
                .onMain()
                .subscribe({ view.onLoadLocalGifs(it) }, {})
                .addTo(disposables)
    }

    fun deleteByOriginalId(localGif: LocalGif) {
        localGifRepository.deleteByOriginalId(localGif.originalId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ view.onSuccessDeleteLocalGif(localGif) }, {})
                .addTo(disposables)
    }

}
