package com.example.loginregister;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("LoginRegister/login.php")
    Call<ApiRes> login( @Field("username") String username,
                        @Field("password") String password );

    @FormUrlEncoded
    @POST("LoginRegister/signup.php")
    Call<ApiRes> signUp( @Field("fullname") String fullname,
                         @Field("email") String email,
                         @Field("username") String username,
                         @Field("password") String password );
}
