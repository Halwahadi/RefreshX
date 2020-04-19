package edu.gsu.refreshx;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {
    @GET("/getuser")
    Call<ApiResult> authenticate(@Query("username") String username, @Query("password") String password);

    @GET("/insert/user")
    Call <ApiResult> createUser(@Query("name") String name, @Query("username") String username, @Query("password") String password, @Query("email") String email);

    @GET("/getdrink")
    Call <ApiResult> getdrink(@Query("id") int id, @Query("dname") String dname, @Query("recipe") String recipe,
                              @Query("warnings") String warnings, @Query("website") String website, @Query("image") String image);
}
