package android.davoh.blogyapp.presentation.home

import android.davoh.api.requests.CreatePostRequest
import android.davoh.api.requests.GeneralResponse
import android.davoh.api.responses.posts.PostsResponse
import android.davoh.api.utils.ResultConsumer
import android.davoh.api.utils.ResultSimple
import android.davoh.blogyapp.repository.PostRepository
import android.davoh.blogyapp.utils.BaseViewModel
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: PostRepository) : BaseViewModel() {
    
    val posts = MutableLiveData<PostsResponse>()
    val created = MutableLiveData<GeneralResponse>()
    
    fun getPosts(){
        loader.postValue(true)
        repository.getPosts(object : ResultConsumer<PostsResponse> {
            override fun consume(result: ResultSimple<PostsResponse>) {
                if (result.isSuccessful() && result.result != null) {
                    posts.postValue(result.result!!)
                } else if (result.isSuccessful()) {
                    emptyResponse.postValue(true)
                } else if (result.isUnauthorized()) {
                    isUnauthenticated.postValue(true)
                } else if (result.isHttpError()) {
                    httpError.postValue(result.error)
                } else {
                    errorNoConnection.postValue(true)
                }
                loader.postValue(false)
            }
        })
    }
    
    fun createPost(title:String, content:String, author:String){
        loader.postValue(true)
        repository.createPost(CreatePostRequest(title, content, author), object : ResultConsumer<GeneralResponse>{
            override fun consume(result: ResultSimple<GeneralResponse>) {
                if (result.isSuccessful() && result.result != null) {
                    created.postValue(result.result!!)
                } else if (result.isSuccessful()) {
                    emptyResponse.postValue(true)
                } else if (result.isUnauthorized()) {
                    isUnauthenticated.postValue(true)
                } else if (result.isHttpError()) {
                    httpError.postValue(result.error)
                } else {
                    errorNoConnection.postValue(true)
                }
                loader.postValue(false)
            }
        })
    }
    
    
}