package sem.ua.androidintern.retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ShibeService {
    @GET("/api/shibes")
    suspend fun getShibes(
        @Query("count") count: Int,
        @Query("urls") urls: Boolean,
        @Query("httpsUrls") httpsUrls: Boolean
    ): Response<List<String>>
}