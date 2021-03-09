package giphy.android.clone.database.gif

import io.reactivex.Single

class LocalGifRepository(
        private val localGifDao: LocalGifDao
) {

    fun insert(localGif: LocalGif) =
            localGifDao.insert(localGif)

    fun deleteByOriginalId(originalId: String) = localGifDao.deleteByOriginalId(originalId)

    fun getAll(): Single<List<LocalGif>> = localGifDao.findAll()

    fun isOriginalIdExist(originalId: String) = localGifDao.existOriginalId(originalId)
}
