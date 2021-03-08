package giphy.android.clone.database.gif

import androidx.room.Entity
import androidx.room.PrimaryKey
import giphy.android.clone.ui.gif.Gif

@Entity(tableName = "local_gif")
data class LocalGif(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    val originalId: String = "",
    val height: String = "",
    val width: String = "",
    val size: String = "",
    val url: String = ""
) {

    companion object {
        fun mapFor(gif: Gif) : LocalGif {
            return LocalGif(
                id = null,
                originalId = gif.id,
                height = gif.getImage().height,
                width = gif.getImage().width,
                size = gif.getImage().size,
                url = gif.getImage().url
            )
        }
    }
}
