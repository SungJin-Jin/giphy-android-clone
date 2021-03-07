package giphy.android.clone.base.preseneter

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class RxPresenter {
    val disposables: CompositeDisposable by lazy { CompositeDisposable() }

    fun add(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun dispose() {
        if (!disposables.isDisposed)
            disposables.dispose()
    }
}