package com.ngapp.metanmobile.data.remote.service

import com.ngapp.metanmobile.data.remote.githubuser.GithubUserInfo
import retrofit2.http.GET

interface GithubService {

    @GET(GITHUB_USER)
    suspend fun getGithubUser(): GithubUserInfo

    companion object {
        const val GITHUB_USER = "users/ngapp-dev"
    }
}