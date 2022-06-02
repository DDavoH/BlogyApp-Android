package android.davoh.api.requests

import com.google.gson.annotations.SerializedName

data class CreatePostRequest (
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("author") val author: String
        )