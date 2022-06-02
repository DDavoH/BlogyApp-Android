package android.davoh.blogyapp.utils

import android.davoh.api.responses.HttpError
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    
    val loader = MutableLiveData<Boolean>()
    val errorNoConnection = MutableLiveData<Boolean>()
    val isUnauthenticated = MutableLiveData<Boolean>()
    val emptyResponse = MutableLiveData<Boolean>()
    val httpError = MutableLiveData<HttpError>()
    
}