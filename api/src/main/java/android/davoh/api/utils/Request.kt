package android.davoh.api.utils

import android.davoh.api.requests.GeneralResponse
import android.util.Log
import com.google.gson.Gson
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response

abstract class Request<Data> {
    val gson = Gson()
    protected abstract fun callToAPI(): Observable<Response<ResponseBody>>
    
    @Throws(HttpException::class)
    protected abstract fun parseResponse(response: ResponseBody?): Data
    
    protected abstract fun errorNoConnection()
    protected abstract fun unauthorized()
    protected abstract fun httpError(error: String, code: Int)
    
    open fun makeRequest(): Observable<Any> {
        return Observable.create { emitter: ObservableEmitter<Any> ->
            try {
                fromNetwork(null, emitter)
            } catch (ex: Throwable) {
                fromNetwork(ex, emitter)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
    }
    
    protected open fun fromNetwork(errorLocal: Throwable?, emitter: ObservableEmitter<Any>) {
        if (errorLocal != null) {
            errorNoConnection()
            return
        }
        try {
            if (emitter.isDisposed) {
                return
            }
            callToAPI().subscribe(
                { response ->
                    if (!emitter.isDisposed) {
                        try {
                            val data = parseResponse(response.body())
                            emitter.onNext(data)
                        } catch (ex: Throwable) {
                            try {
                                if (response.code() != 200) {
                                    showErrorHttp(response)
                                } else {
                                    httpError("Error en la informacion", response.code())
                                }
                            } catch (ex: Throwable) {
                                Log.e("ERROR", "Error al parsear el error")
                            }
                        }
                    }
                }, {
                    Log.e("ERROR", "Error en el observer")
                }
            )
        } catch (th: Throwable) {
            errorNoConnection()
        }
    }
    
    private fun showErrorHttp(response: Response<ResponseBody>) {
        try {
            val errorParsed = gson.fromJson(response.errorBody()?.string(), GeneralResponse::class.java)
            httpError(errorParsed.result?.message ?: "", response.code())
        } catch (e: Exception) {
            errorNoConnection()
        }
    }
    
}