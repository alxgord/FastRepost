package com.artto.fastrepost.data.properties

import android.content.SharedPreferences

class PropertiesDataStore(private val sharedPreferences: SharedPreferences) {

    companion object {
        const val KEY_IS_APP_RATED = "is_app_rated"
    }

    fun load() = sharedPreferences.run {
        Properties(
                getBoolean(KEY_IS_APP_RATED, false)
        )
    }

    fun save(properties: Properties) = with(properties) {
        sharedPreferences.edit()
                .putBoolean(KEY_IS_APP_RATED, isAppRated)
                .apply()
    }

}