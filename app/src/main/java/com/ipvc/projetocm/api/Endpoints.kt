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

}