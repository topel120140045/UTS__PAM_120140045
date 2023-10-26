package com.example.uts_pam_120140045.data.remote

import com.example.uts_pam_120140045.model.ResponseApiUser
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    //get list users with query
    @GET("api/users")
    fun getListUsers(@Query("page") page: String): Call<ResponseApiUser>

    //get list user by id using path
    @GET("api/users/{id}")
    fun getUser(@Path("id") id: String): Call<ResponseApiUser>
}