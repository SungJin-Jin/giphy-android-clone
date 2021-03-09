package giphy.android.clone.extensions

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.onMain(): Observable<T> =
        observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())

fun <T> Single<T>.onMain(): Single<T> = observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())