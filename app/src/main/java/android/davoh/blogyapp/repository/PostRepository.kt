package android.davoh.blogyapp.repository

import android.davoh.api.ApiRest
import android.davoh.api.requests.CreatePostRequest
import android.davoh.api.requests.GeneralResponse
import android.davoh.api.requests.SearchPostRequest
import android.davoh.api.responses.posts.PostsResponse
import android.davoh.api.utils.Request
import android.davoh.api.utils.ResultConsumer
import android.davoh.api.utils.ResultSimple
import android.davoh.blogyapp.utils.BaseRepository
import io.reactivex.rxjava3.core.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class PostRepository @Inject constructor(private val apiRest: ApiRest) : BaseRepository() {
    
    fun getPosts(consumer: ResultConsumer<PostsResponse>){
        object : Request<PostsResponse>() {
            override fun callToAPI(): Observable<Response<ResponseBody>> {
                return apiRest.getPosts()
            }
            
            override fun parseResponse(response: ResponseBody?): PostsResponse {
                return gson.fromJson(response?.string(), PostsResponse::class.java)
            }
            
            override fun errorNoConnection() {
                consumer.consume(ResultSimple(errorConnection = true))
            }
            
            override fun unauthorized() {
                consumer.consume(ResultSimple(unauthorized = true))
            }
            
            override fun httpError(error: String, code: Int) {
                consumer.consume(getResultError(error, code))
            }
            
        }.makeRequest().subscribe { result ->
            consumer.consume(ResultSimple(result as PostsResponse))
        }
    }
    
    fun searchPost(request: SearchPostRequest,consumer: ResultConsumer<PostsResponse>){
        object : Request<PostsResponse>() {
            override fun callToAPI(): Observable<Response<ResponseBody>> {
                return apiRest.searchPost(request)
            }
            
            override fun parseResponse(response: ResponseBody?): PostsResponse {
                return gson.fromJson(response?.string(), PostsResponse::class.java)
            }
            
            override fun errorNoConnection() {
                consumer.consume(ResultSimple(errorConnection = true))
            }
            
            override fun unauthorized() {
                consumer.consume(ResultSimple(unauthorized = true))
            }
            
            override fun httpError(error: String, code: Int) {
                consumer.consume(getResultError(error, code))
            }
            
        }.makeRequest().subscribe { result ->
            consumer.consume(ResultSimple(result as PostsResponse))
        }
    }
    
    fun createPost(request: CreatePostRequest,consumer: ResultConsumer<GeneralResponse>){
        object : Request<GeneralResponse>() {
            override fun callToAPI(): Observable<Response<ResponseBody>> {
                return apiRest.createPost(request)
            }
            
            override fun parseResponse(response: ResponseBody?): GeneralResponse {
                return gson.fromJson(response?.string(), GeneralResponse::class.java)
            }
            
            override fun errorNoConnection() {
                consumer.consume(ResultSimple(errorConnection = true))
            }
            
            override fun unauthorized() {
                consumer.consume(ResultSimple(unauthorized = true))
            }
            
            override fun httpError(error: String, code: Int) {
                consumer.consume(getResultError(error, code))
            }
            
        }.makeRequest().subscribe { result ->
            consumer.consume(ResultSimple(result as GeneralResponse))
        }
    }
    
    
    
}