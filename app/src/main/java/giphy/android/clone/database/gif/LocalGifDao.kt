package giphy.android.clone.database.gif

import androidx.room.*
import giphy.android.clone.ui.gif.Gif
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface LocalGifDao {

    @Insert
    fun insert(localGif: LocalGif): Completable

    @Delete
    fun delete(localGif: LocalGif): Completable

    @Query("SELECT * FROM local_gif ORDER BY id DESC")
    fun findAll(): Observable<List<LocalGif>>

    @Query("SELECT * FROM local_gif WHERE originalId = :originalId LIMIT 1")
    fun findByOriginalId(originalId: String): Observable<LocalGif>
}
