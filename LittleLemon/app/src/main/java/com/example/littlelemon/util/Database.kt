package com.example.littlelemon.util

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson


data class User(
    val firstName: String,
    val lastName: String,
    val email: String
)

object SharedPreferencesHelper {
    private const val PREFS_NAME = "UserPrefs"
    private const val KEY_USER_JSON = "userJson"

    fun saveUserAsJson(context: Context, user: User) {
        val gson = Gson()
        val userJson = gson.toJson(user)

        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        editor.putString(KEY_USER_JSON, userJson)
        editor.apply()
    }

    fun getUserFromSharedPreferences(context: Context): User? {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        val userJson = sharedPreferences.getString(KEY_USER_JSON, null)

        return if (userJson != null) {
            val gson = Gson()
            gson.fromJson(userJson, User::class.java)
        } else {
            null
        }
    }

    fun clearSharedPreferences(context: Context) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}
