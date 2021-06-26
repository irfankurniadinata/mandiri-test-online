package com.irfan.mandiriTestOption2.data.api

import com.irfan.mandiriTestOption2.data.models.ArticleResponse
import com.irfan.mandiriTestOption2.data.models.SourceResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {
    @GET("/v2/everything")
    fun getNews(
        @Query("sources") sources: String?,
        @Query("q") q: String?,
        @Query("from") from: String?,
        @Query("sortBy") sortBy: String?,
        @Query("apiKey") apiKey: String?,
        @Query("page") page: Int?,
        @Query("pageSize") pageSize: Int?
    ): Single<ArticleResponse>

    @GET("/v2/sources")
    fun getSource(
        @Query("category") category: String?,
        @Query("apiKey") apiKey: String?
    ): Single<SourceResponse>
}