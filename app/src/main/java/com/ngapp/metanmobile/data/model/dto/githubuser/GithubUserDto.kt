package com.ngapp.metanmobile.data.model.dto.githubuser

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUserDto(
    val id: Int,
    val login: String,
    val avatarUrl: String?,
    val url: String,
    val htmlUrl: String,
    val name: String?,
    val company: String?,
    val blog: String?,
    val location: String?,
    val email: String?,
    val bio: String?,
    val twitterUsername: String?
) : Parcelable {
    companion object {
        fun init() = GithubUserDto(
            id = 49076456,
            login = "ngapp-dev",
            avatarUrl = "https://avatars.githubusercontent.com/u/49076456?v=4",
            url = "https://api.github.com/users/ngapp-dev",
            htmlUrl = "https://github.com/ngapp-dev",
            name = "NGApps Dev",
            company = null,
            blog = "https://metan.by/",
            location = "Poznan",
            email = null,
            bio = null,
            twitterUsername = null
        )
    }
}