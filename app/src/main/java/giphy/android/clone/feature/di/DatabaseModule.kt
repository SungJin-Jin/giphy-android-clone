package giphy.android.clone.feature.di

import giphy.android.clone.database.AppDatabase
import giphy.android.clone.database.gif.LocalGifRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val databaseModule = module {
    factory { AppDatabase.build(androidApplication()) }
    single { get<AppDatabase>().getLocalGifDao() }

    single { LocalGifRepository(get()) }

}
