package android.davoh.blogyapp.utils

import android.davoh.api.responses.HttpError
import android.davoh.api.utils.ResultSimple
import com.google.gson.Gson
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

open class BaseRepository {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val gson = Gson()
    
    private fun addDisposable(d: Disposable) {
        compositeDisposable.add(d)
    }
    
    fun setDisposable(d: Disposable) {
        clear()
        addDisposable(d)
    }
    
    private fun clear() {
        compositeDisposable.clear()
    }
    
    fun <T> getResultError(error: String, code: Int) = ResultSimple<T>(
        error = HttpError(error, code)
    )
}