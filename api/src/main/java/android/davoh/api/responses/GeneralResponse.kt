package android.davoh.api.requests

import com.google.gson.annotations.SerializedName

data class GeneralResponse (
    @SerializedName("result" ) var result : GeneralResponseResult? = GeneralResponseResult()
)

data class GeneralResponseResult (
    @SerializedName("error"   ) var error   : Boolean? = null,
    @SerializedName("message" ) var message : String?  = null
)