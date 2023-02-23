package com.olegnew.jvretrofitgetusers.service;

import com.olegnew.jvretrofitgetusers.model.UsersData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ItemService {
    @GET("/2.3/users")
    Call<UsersData> getitemsList(@Query("page") int page,
                                 @Query("pagesize") int pagesize,
                                 @Query("order") String  order,
                                 @Query("sort") String  sort,
                                 @Query("min") int min,
                                 @Query("site") String  site);
}
