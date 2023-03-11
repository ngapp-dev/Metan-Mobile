package com.ngapp.metanmobile.data.remote.githubuser

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GithubUserInfo(
    @Json(name = "login") val login: String?,
    @Json(name = "id") val id: Int?,
    @Json(name = "avatar_url") val avatarUrl: String?,
    @Json(name = "url") val url: String?,
    @Json(name = "html_url") val htmlUrl: String?,
    @Json(name = "name") val name: String?,
    @Json(name = "company") val company: String?,
    @Json(name = "blog") val blog: String?,
    @Json(name = "location") val location: String?,
    @Json(name = "email") val email: String?,
    @Json(name = "bio") val bio: String?,
    @Json(name = "twitter_username") val twitterUsername: String?
)