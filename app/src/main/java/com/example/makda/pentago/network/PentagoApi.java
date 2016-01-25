package com.example.makda.pentago.network;

import com.example.makda.pentago.model.User;

import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Query;

/**
 * Created by Makda on 2016-01-18.
 */
public interface PentagoApi {
    @GET("/login")
    Boolean getUser(@Query("name") String name, @Query("password") String password);

    @PUT("/login")
    Void createUser(@Query("name") String name, @Query("password") String password, @Query("email") String email);

    @PUT("/shape")
    Void createShapeStatisticItem(@Query("login") String name, @Query("shape") String shape);

    @GET("/shape")
    String getFavoriteBoard(@Query("login") String name);

    @PUT("/partner")
    Void createPartnerStatisticItem(@Query("login") String name, @Query("player") String player);

    @GET("/partner")
    String getFavoritePartner(@Query("login") String name);

    @DELETE("/login")
     Void deleteUser(@Query("name") String name, @Query("password") String password);

    @POST("/login")
    Void updatePassword(@Query("name") String name, @Query("password") String password,
                               @Query("new_password") String newPassword);

    @GET("/wonstatistic")
    Double getWonStats(@Query("name") String name);
}