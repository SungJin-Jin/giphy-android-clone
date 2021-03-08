package giphy.android.clone

import androidx.multidex.MultiDexApplication
import giphy.android.clone.feature.di.databaseModule
import giphy.android.clone.feature.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GiphyApplication : MultiDexApplication() {

    init {
        instance = this
    }

    companion object {
        private var instance: GiphyApplication? = null

        fun context() = instance!!

    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(context())
            modules(networkModule)
            modules(databaseModule)
        }

    }

}