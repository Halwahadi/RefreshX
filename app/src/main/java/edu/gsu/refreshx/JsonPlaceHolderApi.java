package edu.gsu.refreshx;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {
    @GET("/getuser")
    Call<ApiResult> authenticate(@Query("username") String username, @Query("password") String password);

    @GET("/insert/user")
    Call <ApiResult> createUser(@Query("name") String name, @Query("username") String username, @Query("password") String password, @Query("email") String email);

}
