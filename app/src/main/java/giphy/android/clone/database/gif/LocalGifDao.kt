package giphy.android.clone.database.gif

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface LocalGifDao {

    @Insert
    fun insert(localGif: LocalGif): Completable

    @Query("DELETE FROM local_gif WHERE originalId = :originalId")
    fun deleteByOriginalId(originalId: String): Completable

    @Query("SELECT * FROM local_gif ORDER BY id DESC")
    fun findAll(): Single<List<LocalGif>>

    @Query("SELECT EXISTS(SELECT * FROM local_gif WHERE originalId = :originalId)")
    fun existOriginalId(originalId: String): Single<Boolean>
}
