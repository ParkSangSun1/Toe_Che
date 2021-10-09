package com.children.toyexchange.presentation.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.children.toyexchange.presentation.di.DataStoreModule.PreferencesKeys.dataStoreUid
import com.children.toyexchange.presentation.widget.utils.DataStore.DEFAULT_UID
import com.children.toyexchange.presentation.widget.utils.DataStore.PREFERENCES_UID
import com.children.toyexchange.presentation.widget.utils.DataStore.PREFERENCE_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject


@ActivityRetainedScoped
class DataStoreModule @Inject constructor(@ApplicationContext private val context: Context) {


}