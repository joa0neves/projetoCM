package com.ipvc.projetocm.api

import com.ipvc.projetocm.Model.Id
import com.ipvc.projetocm.Model.Utilizador
import retrofit2.Call
import retrofit2.http.*

interface EndPoints {

    @FormUrlEncoded
    @POST("postUser.php")
    fun postUser(@Field("nome") nome: String?,
                 @Field("email") email: String?,
                 @Field("password") password: String?,
                 @Field("contacto") contacto: String?): Call<DefaultResponse>

    @FormUrlEncoded
    @POST("listUser.php")
    fun getIndividualUser(@Field("id") id: Int?) : Call<Utilizador>

    @FormUrlEncoded
    @POST("postReview.php")
    fun postReview(@Field("estrelas") estrelas: Double?,
                   @Field("descr") descr: String?,
                   @Field("idUser") idUser: Int?
    ): Call<DefaultResponse>

    @FormUrlEncoded
    @POST("login.php")
    fun postLogin(@Field("email") email: String?,
                   @Field("password") password: String?): Call<Id>

    @FormUrlEncoded
    @POST("postBilhete.php")
    fun postBilhete(@Field("data") data: String?,
                 @Field("tempo") tempo: Int?,
                 @Field("valor") valor: Double?,
                 @Field("idUser") idUser: Int?): Call<DefaultResponse>

    @FormUrlEncoded
    @POST("updatePagamento.php")
    fun updatePagamento(@Field("id") data: Int?): Call<DefaultResponse>

    @FormUrlEncoded
    @POST("getIdBilhete.php")
    fun getIdBilhete(@Field("idUser") idUser: Int?): Call<Id>
    
    @POST("updateUser.php")
    fun updateUser(@Field("id") id: Int?,
                @Field("nome") nome: String?,
                 @Field("email") email: String?,
                 @Field("contacto") contacto: String?): Call<DefaultResponse>

}