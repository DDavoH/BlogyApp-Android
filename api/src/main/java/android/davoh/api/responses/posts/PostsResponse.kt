package android.davoh.api.responses.posts

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class PostsResponse (
    @SerializedName("result" ) var result : PostsResponseResult? = PostsResponseResult()
)

data class PostsResponseResult (
    @SerializedName("error" ) var error : Boolean? = null,
    @SerializedName("data"  ) var data  : PostsResponseResultData?    = PostsResponseResultData()
)

data class PostsResponseResultData (
    @SerializedName("posts" ) var posts : ArrayList<Posts> = arrayListOf()
)

@Parcelize
data class Posts (
    @SerializedName("id"         ) var id        : Int?    = null,
    @SerializedName("title"      ) var title     : String? = null,
    @SerializedName("author"     ) var author    : String? = null,
    @SerializedName("content"    ) var content   : String? = null,
    @SerializedName("created_at" ) var createdAt : String? = null,
    @SerializedName("updated_at" ) var updatedAt : String? = null
): Parcelable