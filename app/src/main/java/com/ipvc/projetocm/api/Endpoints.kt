package com.ipvc.projetocm.api

import retrofit2.Call
import retrofit2.http.*

interface EndPoints {

    @FormUrlEncoded
    @POST("postUser.php")
    fun postUser(@Field("nome") nome: String?,
                 @Field("email") email: String?,
                 @Field("password") password: String?,
                 @Field("contacto") contacto: String?): Call<DefaultResponse>

    @GET("listUser.php")
    fun getIndividualUser(@Field("id") id: Int?) : Call<DefaultResponse>

    @FormUrlEncoded
    @POST("postReview.php")
    fun postReview(@Field("estrelas") estrelas: Double?,
                 @Field("descr") descr: String?,
                 @Field("idUser") idUser: Int?): Call<DefaultResponse>

    @FormUrlEncoded
    @POST("login.php")
    fun postLogin(@Field("email") email: String?,
                   @Field("password") password: String?): Call<DefaultResponse>

}