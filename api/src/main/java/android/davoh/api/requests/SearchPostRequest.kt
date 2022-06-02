package android.davoh.api.requests

import com.google.gson.annotations.SerializedName

data class SearchPostRequest (
    @SerializedName("query") val query: String
        )