package giphy.android.clone.database.gif

import io.reactivex.Observable

class LocalGifRepository(
        private val localGifDao: LocalGifDao
) {

    fun insert(localGif: LocalGif) =
            localGifDao.insert(localGif)

    fun delete(localGif: LocalGif) =
            localGifDao.delete(localGif)

    fun getAll(): Observable<List<LocalGif>> = localGifDao.findAll()

    fun getByOriginalId(originalId: String) = localGifDao.findByOriginalId(originalId)
}
