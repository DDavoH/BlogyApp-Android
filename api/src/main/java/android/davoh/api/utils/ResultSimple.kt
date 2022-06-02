package android.davoh.api.utils

import android.davoh.api.responses.HttpError
import java.io.Serializable

data class ResultSimple<T>(
    var result: T ?= null,
    var unauthorized: Boolean ?= false,
    var isLoading: Boolean ?= false,
    var errorConnection: Boolean ?= false,
    var error: HttpError?= null


) : Serializable {
    fun isSuccessful() : Boolean {return this.result != null}
    fun isUnauthorized() : Boolean { return this.unauthorized == true}
    fun isErrorNoConnection() : Boolean { return this.errorConnection == true}
    fun isHttpError() : Boolean {return this.error != null}
}
