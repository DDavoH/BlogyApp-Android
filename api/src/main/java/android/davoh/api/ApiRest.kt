package android.davoh.api

import android.davoh.api.requests.CreatePostRequest
import android.davoh.api.requests.SearchPostRequest
import io.reactivex.rxjava3.core.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiRest {

    @GET("posts")
    fun getPosts() : Observable<Response<ResponseBody>>
    
    @POST("create-post")
    fun createPost(@Body request: CreatePostRequest)  : Observable<Response<ResponseBody>>
    
    @POST("post/search")
    fun searchPost(@Body request: SearchPostRequest) : Observable<Response<ResponseBody>>
    

}