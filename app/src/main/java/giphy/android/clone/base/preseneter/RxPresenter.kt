package giphy.android.clone.base.preseneter

import io.reactivex.disposables.CompositeDisposable

open class RxPresenter {
    val disposables: CompositeDisposable by lazy { CompositeDisposable() }

    fun dispose() {
        if (!disposables.isDisposed)
            disposables.dispose()
    }
}