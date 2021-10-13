package com.children.toyexchange.data.repository

import com.children.toyexchange.data.repository.datasource.RemoteSplashDataSource
import com.children.toyexchange.domain.repository.SplashRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import javax.inject.Inject

class SplashRepositoryImpl @Inject constructor(
    private val splashDataSource: RemoteSplashDataSource
) : SplashRepository {
    override fun checkAppVersion(myVersion: String): Task<DataSnapshot> {
        return splashDataSource.checkAppVersion(myVersion)
    }
}