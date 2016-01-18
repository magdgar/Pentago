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
    public User getUser(@Query("name") String name, @Query("password") String password);

    @PUT("/login")
    public Void createUser(@Query("name") String name, @Query("password") String password);

    @DELETE("/login")
    public Void deleteUser(@Query("name") String name, @Query("password") String password);

    @POST("/login")
    public Void updatePassword(@Query("name") String name, @Query("password") String password,
                               @Query("new_password") String newPassword);
}