package com.ngapp.metanmobile.data.repository.profile

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.withTransaction
import com.ngapp.metanmobile.data.local.dao.ProfileDao
import com.ngapp.metanmobile.data.local.db.MetanMobileDatabase
import com.ngapp.metanmobile.data.model.dto.faq.FaqDto
import com.ngapp.metanmobile.data.model.dto.faq.toFaqDtoList
import com.ngapp.metanmobile.data.model.dto.location.toLocationEntity
import com.ngapp.metanmobile.data.model.local.profile.ProfileEntity
import com.prof.rssparser.Parser
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val database: MetanMobileDatabase
) {

    suspend fun deleteProfile() = database.profileDao().deleteProfile()

    suspend fun getProfile() = database.profileDao().getProfile()

    suspend fun updateProfile(profile: ProfileEntity) {
        database.withTransaction {
            profile.let {
                database.profileDao().deleteProfile()
                database.profileDao().insertProfile(it)
            }
        }
    }
}